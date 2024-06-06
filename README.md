# Duplicate Photo Handler

🚀 **Duplicate Photo Handler** is a Java application designed to help you manage and clean up duplicate photos in your folders. Using a HashSet data structure, this tool efficiently identifies duplicate images and organizes them into a designated folder.

## Features

- **Efficient Duplicate Detection**: Utilizes a HashSet to quickly identify duplicate photos.
- **Progress Updates**: Displays progress updates after processing every 100 photos.
- **Organized Cleanup**: Moves duplicate photos to a "DuplicatePhotos" folder for easy management.
- **User-Friendly Output**: Prints a message if no duplicates are found.

## How It Works

1. **Input**: Provide the path to the folder you want to clean up.
2. **Processing**: The application scans the folder, processes the photos, and identifies duplicates.
3. **Output**: Duplicate photos are moved to the "DuplicatePhotos" folder, and progress updates are displayed throughout the process.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/YourGitHubUsername/DuplicatePhotoHandler.git
    ```
2. Navigate to the project directory:
    ```bash
    cd DuplicatePhotoHandler
    ```

## Usage

1. Compile the Java file:
    ```bash
    javac DuplicatePhotoHandler.java
    ```
2. Run the application:
    ```bash
    java DuplicatePhotoHandler
    ```
3. When prompted, enter the path to the folder you wish to clean:
    ```bash
    Enter the folder path: /path/to/your/folder
    ```

## Example

```java
Enter the folder path: /Users/YourUsername/Pictures
100 files processed...
200 files processed...
Duplicates found and moved to DuplicatePhotos folder.
```

## Contributing

Feel free to fork this project, submit issues, and send pull requests. Any contributions are welcome!
