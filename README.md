# DiplomaGen – Simple Diploma Generator

DiplomaGen is a small Java application that generates a `.docx` diploma file using **Apache POI**.  
It provides a simple **desktop (JavaFX) user interface** where the user can enter diploma text and select an image, then generates a ready-to-edit Word document.  
This project is designed to be modular and extensible, **setting the foundation for future automation**, such as batch diploma generation or integration with external systems.

## Features
- Desktop (JavaFX) UI
- Inserts an image into a generated `.docx` file  
  ⚠️ **Only PNG images are supported**
- Adds formatted text:
  - Title
  - Subtitle
  - Award text
- User-defined fields for:
  - Recipient name
  - Details (time / date / other text)
- Outputs a ready-to-edit `Diploma.docx`

## Requirements
- Java 21+
- Maven
- Apache POI (`poi-ooxml`, managed via Maven)
- JavaFX (handled via Maven plugin)

## Project Structure
This is a multi-module Maven project:

- `diploma-model` – Shared data structures
- `diploma-core` – Business logic and document generation
- `diploma-ui-desktop` – JavaFX desktop application

## How It Works
1. The user enters diploma text in the JavaFX UI
2. A PNG image is selected from the local file system
3. The UI validates the input
4. The core module generates a `.docx` file using Apache POI
5. The output is saved as **Diploma.docx**, in project root

Apache POI handles Word document creation, text styling, and image embedding (using EMU units for sizing).

## Running the Program (Desktop UI)

From the **project root**, run:

```bash
mvn clean javafx:run -pl diploma-ui-desktop
```
This will launch the JavaFX desktop application.

## Author
**Anton Jansson**  