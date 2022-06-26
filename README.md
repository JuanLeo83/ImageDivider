[![Kotlin](https://img.shields.io/badge/Kotlin-1.7.0-success.svg)](http://kotlinlang.org/)
[![ImageDivider](https://img.shields.io/badge/ImageDivider-1.0.0-success.svg)](http://kotlinlang.org/)

# ImageDivider

ImageDivider is a spritesheet divider. You only need a json file with the description of 
what you need and you will get each image of the animations separately and with the 
corresponding name.

## How to use

Extract the contents of the zip and run ImageDivider.bat or the sh version from your terminal 
indicating the path of the configuration file.

```Batch
.\ImageDivider-1.0.0\bin\ImageDivider.bat .\exampleFiles\config.json
```

if you use a relative path, it must be relative to the folder you are running the script from.
In the example above this would be the case.

![ImageDivider_folder_tree_example](https://user-images.githubusercontent.com/11087285/175806025-1a3ed9dd-9445-4c58-a6f6-479c2f1c5a6f.png)

### config.json file example

```json
{
  "imagePath": "./exampleFiles/slime.png",
  "widthFrames": 7,
  "heightFrames": 5,
  "animations": [
    {
      "name": "idle",
      "row": 0,
      "frames": 4
    },{
      "name": "run",
      "row": 1,
      "frames": 6
    },{
      "name": "die",
      "row": 4,
      "frames": 5
    }
  ],
  "outputFolder": "slime"
}
```

- imagePath: relative path from config.json to spritesheet.
- widthFrames: total frames horizontally.
- heightFrames: total frames vertically.
- animations: list of animations you want to split into images.
- Each animation has a name that will be assigned to each frame followed by the frame index. You must indicate the row in which it is located and finally the number of frames that make up the animation.
- outputFolder: name of the folder where the new images will be generated. If this folder does not exist, it will be created before adding the images.

## Limitations

An animation is supposed to be in one row of the spritesheet, so if it's split across multiple rows it may not get the expected result.