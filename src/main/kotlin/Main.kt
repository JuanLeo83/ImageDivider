import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import model.Animation
import model.Config
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_ARGB
import java.io.File
import java.io.FileReader
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    if (args.isEmpty()) return

    val config = getConfig(args[0])
    if (config == null) {
        System.err.println("There was an error parsing config file. Please, review ${args[0]} file")
        return
    }

    val image = readFile(config)
    divideImage(config, image)

    println("Image divided!")
}

private fun getConfig(filePath: String): Config? {
    val gson = Gson()
    val reader = JsonReader(FileReader(filePath))
    return gson.fromJson<Config>(reader, Config::class.java)
}

private fun readFile(config: Config): BufferedImage {
    with(config) {
        val file = File(imagePath)
        return ImageIO.read(file)
    }
}

private fun divideImage(config: Config, image: BufferedImage) {
    val frameWidth = image.width / config.widthFrames
    val frameHeight = image.height / config.heightFrames

    config.animations.forEach { animation ->
        with(animation) {
            for (frame in 0 until frames) {
                val xPosition = frameWidth * frame
                val yPosition = frameHeight * row

                val imageToWrite = BufferedImage(frameWidth, frameHeight, TYPE_INT_ARGB)
                imageToWrite.createGraphics()
                    .drawImage(
                        image,
                        0,
                        0,
                        frameWidth,
                        frameHeight,
                        xPosition,
                        yPosition,
                        xPosition + frameWidth,
                        yPosition + frameHeight,
                        null
                    )

                saveImage(config, this, frame, imageToWrite)
            }
        }
    }
}

private fun saveImage(config: Config, animation: Animation, frameIndex: Int, image: BufferedImage) {
    val outputFolder = File("./${config.outputFolder}")
    if (!outputFolder.exists()) {
        outputFolder.mkdir()
    }

    val outputFile = File("./${config.outputFolder}/${animation.name}_${formatFrameIndex(frameIndex)}.png")
    ImageIO.write(image, "png", outputFile)
}

private fun formatFrameIndex(frameIndex: Int): String {
    return if (frameIndex < 10) "0$frameIndex" else frameIndex.toString()
}



