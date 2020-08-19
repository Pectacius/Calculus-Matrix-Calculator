package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

//Helper class with static methods that stores all fonts used. Helps create custom fonts.
public class CustomFonts {
    private static Font buttonFont;

    //MODIFIES: this
    //EFFECTS: makes a font with specified font name, format and size
    public static Font makeFont(String font, String format, float size) {
        try {
            if (font.equals("exan")) {
                makeExanFont(format, size);
            } else {
                makeFMPointifaxFont(format, size);
            }
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(buttonFont);
            return buttonFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    //MODIFIES: this
    //EFFECTS: makes a FMPointifax font with specified format and size
    private static void makeFMPointifaxFont(String format, float size) throws IOException, FontFormatException {
        if (format.equals("bold")) {
            buttonFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("./data/Typodermic - NasalizationRg-Regular.otf")).deriveFont(Font.BOLD, size);
        } else {
            buttonFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("./data/Typodermic - NasalizationRg-Regular.otf")).deriveFont(size);
        }
    }

    //MODIFIES: this
    //EFFECTS: makes a Exan font with specified format and size
    private static void makeExanFont(String format, float size) throws IOException, FontFormatException {
        if (format.equals("bold")) {
            buttonFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("./data/Exan-Regular.ttf")).deriveFont(Font.BOLD, size);
        } else {
            buttonFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("./data/Exan-Regular.ttf")).deriveFont(size);
        }
    }
}
