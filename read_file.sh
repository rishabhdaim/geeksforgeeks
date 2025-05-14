#!/bin/bash

# Default output file
OUTPUT_FILE="output.txt"

# Parse command line options
while [ "$#" -gt 0 ]; do
    case "$1" in
        -o|--output)
            if [ -n "$2" ]; then
                OUTPUT_FILE="$2"
                shift 2
            else
                echo "Error: Output filename required after -o/--output" >&2
                exit 1
            fi
            ;;
        *)
            # Save first non-option as input filename
            if [ -z "$INPUT_FILE" ]; then
                INPUT_FILE="$1"
            fi
            shift
            ;;
    esac
done

# Create/clear the output file
true > "$OUTPUT_FILE"

# Function to log messages to console only
log_console() {
    echo "$@"
}

# Function to process a command
process_command() {
    local cmd="$1"
    local retry_count=0
    local max_retries=2

    # Skip empty commands
    [ -z "$cmd" ] && return

    log_console "Processing: $cmd"

    # Execute command and retry up to max_retries times
    while [ $retry_count -le $max_retries ]; do
        # Capture command output
        output=$(sky use "$cmd" 2>&1)
        exit_status=$?

        # Print the command output to console
        log_console "Command output:"
        log_console "$output"

        # Save only lines starting with "ethos" to the output file
        echo "$output" | grep "^ethos" >> "$OUTPUT_FILE"

        if [ $exit_status -eq 0 ]; then
            log_console "Successfully executed 'sky use' for: $cmd"
            return 0
        else
            retry_count=$((retry_count + 1))
            if [ $retry_count -le $max_retries ]; then
                sleep_time=$retry_count  # Sleep for increasing seconds (1, 2, 3...)
                log_console "Command failed with exit code $exit_status, retry $retry_count of $max_retries in $sleep_time seconds..."
                sleep $sleep_time
            else
                log_console "Command failed after $max_retries retries, giving up."
                return $exit_status
            fi
        fi
    done
}

# Check if we're receiving input from a pipe
if [ ! -t 0 ]; then
    # Reading from pipe (stdin)
    log_console "Reading from piped input"
    log_console "Using output file: $OUTPUT_FILE"

    # Read input line by line
    while IFS= read -r line; do
        # Split line by semicolons and process each part
        IFS=';' read -ra commands <<< "$line"
        for cmd in "${commands[@]}"; do
            # Trim leading/trailing whitespace from the command
            cmd=$(echo "$cmd" | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//')
            # Skip empty commands after trimming
            [ -z "$cmd" ] && continue
            process_command "$cmd"
        done
    done
elif [ -z "$INPUT_FILE" ]; then
    # No pipe input and no filename provided
    log_console "Usage: $0 [options] filename"
    log_console "   or: echo 'command' | $0 [options]"
    log_console "Options:"
    log_console "  -o, --output FILENAME   Specify output file (default: output.txt)"
    exit 1
else
    # Check if file exists
    if [ ! -f "$INPUT_FILE" ]; then
        log_console "Error: File $INPUT_FILE does not exist"
        exit 1
    fi

    log_console "Reading from file: $INPUT_FILE"
    log_console "Using output file: $OUTPUT_FILE"

    # Process file line by line
    while IFS= read -r line; do
        # Skip empty lines and lines starting with "PROGRAM"
        [ -z "$line" ] || [[ "$line" == PROGRAM* ]] && continue

        # Check if line starts with "ethos"
        if [[ "$line" == ethos* ]]; then
            # Split the line by whitespace and take only the first 3 entries
            read -r first second third rest <<< "$line"
            process_command "$first $second $third"
        else
            # Process the entire line as before
            process_command "$line"
        fi
    done < "$INPUT_FILE"
fi

log_console "All commands completed successfully"