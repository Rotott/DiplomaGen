# DiplomaGen – Simple Diploma Generator

DiplomaGen is a small Java application that generates a `.docx` diploma file using **Apache POI**.  
It inserts an image, adds a title, fills in static text, and leaves blank spaces for a contestant’s name, time, and date.

## Features
- Inserts a PNG image into a generated `.docx` file
- Adds formatted text (title, subtitle, award line)
- Provides blank fields for:
    - First & last name
    - Time
    - Date
- Outputs a ready-to-edit `Diploma.docx`

## Requirements
- Java 22+
- Maven
- Apache POI (`poi-ooxml` dependency included in `pom.xml`)
- A PNG image located at:  
  `src/main/java/org/example/onion.png`

## How It Works
The generator:
1. Creates a new Word document
2. Embeds the given PNG image
3. Writes the diploma text with formatting
4. Saves the output as **Diploma.docx** in the project root

Apache POI handles DOCX creation, text styling, and the picture embedding (using EMU units for sizing).

## Running the Program
Compile and run using Maven


## Author
**Anton Jansson**  