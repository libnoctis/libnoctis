/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.

 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.input.keyboard;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The Key enumeration
 *
 * <p>
 *     An enumeration of all the different keys with
 *     their code, and their character if they have one.
 * </p>
 *
 * @author Litarvan (Keys from LWJGL)
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Key
{
    // Got the keys from LWJGL, thanks you guys !
    KEY_NONE(0x00),
    KEY_ESCAPE(0x01),
    KEY_1(0x02, '1'),
    KEY_2(0x03, '2'),
    KEY_3(0x04, '3'),
    KEY_4(0x05, '4'),
    KEY_5(0x06, '5'),
    KEY_6(0x07, '6'),
    KEY_7(0x08, '7'),
    KEY_8(0x09, '8'),
    KEY_9(0x0A, '9'),
    KEY_0(0x0B, '0'),
    KEY_MINUS(0x0C, '-'),
    KEY_EQUALS(0x0D, '='),
    KEY_BACK(0x0E),
    KEY_TAB(0x0F),
    KEY_Q(0x10, 'q', 'Q'),
    KEY_W(0x11, 'w', 'W'),
    KEY_E(0x12, 'e', 'E'),
    KEY_R(0x13, 'r', 'R'),
    KEY_T(0x14, 't', 'T'),
    KEY_Y(0x15, 'y', 'Y'),
    KEY_U(0x16, 'u', 'U'),
    KEY_I(0x17, 'i', 'I'),
    KEY_O(0x18, 'o', 'O'),
    KEY_P(0x19, 'p', 'P'),
    KEY_LBRACKET(0x1A),
    KEY_RBRACKET(0x1B),
    KEY_RETURN(0x1C),
    KEY_LCONTROL(0x1D),
    KEY_A(0x1E, 'a', 'A'),
    KEY_S(0x1F, 's', 'S'),
    KEY_D(0x20, 'd', 'D'),
    KEY_F(0x21, 'f', 'F'),
    KEY_G(0x22, 'g', 'G'),
    KEY_H(0x23, 'h', 'H'),
    KEY_J(0x24, 'j', 'J'),
    KEY_K(0x25, 'k', 'K'),
    KEY_L(0x26, 'l', 'L'),
    KEY_SEMICOLON(0x27, ';'),
    KEY_APOSTROPHE(0x28, '\''),
    KEY_GRAVE(0x29, '`'),
    KEY_LSHIFT(0x2A),
    KEY_BACKSLASH(0x2B, '\\'),
    KEY_Z(0x2C, 'z', 'Z'),
    KEY_X(0x2D, 'x', 'X'),
    KEY_C(0x2E, 'c', 'C'),
    KEY_V(0x2F, 'v', 'V'),
    KEY_B(0x30, 'b', 'B'),
    KEY_N(0x31, 'n', 'N'),
    KEY_M(0x32, 'm', 'M'),
    KEY_COMMA(0x33, ','),
    KEY_PERIOD(0x34, '.'),
    KEY_SLASH(0x35, '/'),
    KEY_RSHIFT(0x36),
    KEY_MULTIPLY(0x37, '*'),
    KEY_LMENU(0x38),
    KEY_SPACE(0x39, ' '),
    KEY_CAPITAL(0x3A),
    KEY_F1(0x3B),
    KEY_F2(0x3C),
    KEY_F3(0x3D),
    KEY_F4(0x3E),
    KEY_F5(0x3F),
    KEY_F6(0x40),
    KEY_F7(0x41),
    KEY_F8(0x42),
    KEY_F9(0x43),
    KEY_F10(0x44),
    KEY_NUMLOCK(0x45),
    KEY_SCROLL(0x46),
    KEY_NUMPAD7(0x47, '7'),
    KEY_NUMPAD8(0x48, '8'),
    KEY_NUMPAD9(0x49, '9'),
    KEY_SUBTRACT(0x4A, '/'),
    KEY_NUMPAD4(0x4B, '4'),
    KEY_NUMPAD5(0x4C, '5'),
    KEY_NUMPAD6(0x4D, '6'),
    KEY_ADD(0x4E, '+'),
    KEY_NUMPAD1(0x4F, '1'),
    KEY_NUMPAD2(0x50, '2'),
    KEY_NUMPAD3(0x51, '3'),
    KEY_NUMPAD0(0x52, '0'),
    KEY_DECIMAL(0x53, '.'),
    KEY_F11(0x57),
    KEY_F12(0x58),
    KEY_F13(0x64),
    KEY_F14(0x65),
    KEY_F15(0x66),
    KEY_F16(0x67),
    KEY_F17(0x68),
    KEY_F18(0x69),
    KEY_KANA(0x70),
    KEY_F19(0x71),
    KEY_CONVERT(0x79),
    KEY_NOCONVERT(0x7B),
    KEY_YEN(0x7D),
    KEY_NUMPADEQUALS(0x8D, '='),
    KEY_CIRCUMFLEX(0x90, '^'),
    KEY_AT(0x91),
    KEY_COLON(0x92),
    KEY_UNDERLINE(0x93),
    KEY_KANJI(0x94),
    KEY_STOP(0x95),
    KEY_AX(0x96),
    KEY_UNLABELED(0x97),
    KEY_NUMPADENTER(0x9C),
    KEY_RCONTROL(0x9D),
    KEY_SECTION(0xA7),
    KEY_NUMPADCOMMA(0xB3),
    KEY_DIVIDE(0xB5),
    KEY_SYSRQ(0xB7),
    KEY_RMENU(0xB8),
    KEY_FUNCTION(0xC4),
    KEY_PAUSE(0xC5),
    KEY_HOME(0xC7),
    KEY_UP(0xC8),
    KEY_PRIOR(0xC9),
    KEY_LEFT(0xCB),
    KEY_RIGHT(0xCD),
    KEY_END(0xCF),
    KEY_DOWN(0xD0),
    KEY_NEXT(0xD1),
    KEY_INSERT(0xD2),
    KEY_DELETE(0xD3),
    KEY_CLEAR(0xDA),
    KEY_LMETA(0xDB),
    KEY_RMETA(0xDC),
    KEY_APPS(0xDD),
    KEY_POWER(0xDE),
    KEY_SLEEP(0xDF);

    /**
     * The key code
     */
    private int code;

    /**
     * The key character
     */
    @Nullable
    private Character character;

    /**
     * The key upper character
     */
    @Nullable
    private Character upperCharacter;

    /**
     * A non-character key
     *
     * @param code The key code
     */
    Key(int code)
    {
        this.code = code;
    }

    /**
     * A character key
     *
     * @param character The key character (if it is a character key)
     * @param code The key code
     */
    Key(int code, @Nullable Character character)
    {
        this(code);
        this.character = character;
    }

    /**
     * A character key
     *
     * @param code The key code
     * @param character The key character
     * @param upperCharacter The key upper character
     */
    Key(int code, @Nullable Character character, @Nullable Character upperCharacter)
    {
        this(code, character);
        this.upperCharacter = upperCharacter;
    }

    /**
     * @return The key character if it is a character key
     */
    @Nullable
    public Character getCharacter()
    {
        return character;
    }

    /**
     * @return The key upper character if it has one
     */
    @Nullable
    public Character getUpperCharacter()
    {
        return upperCharacter;
    }

    /**
     * @return The key code
     */
    public int getCode()
    {
        return code;
    }

    /**
     * @return If the key is a character
     */
    public boolean isCharacter()
    {
        return character != null;
    }

    /**
     * @return If the key has an upper character
     */
    public boolean hasUpperCharacter()
    {
        return upperCharacter != null;
    }

    /**
     * Get the key object of the given key code
     *
     * @param code The code of the key to get
     *
     * @return The found key, or KEY_NONE if not found
     */
    @NotNull
    public static Key byCode(int code)
    {
        for (Key key : values())
            if (key.getCode() == code)
                return key;

        return KEY_NONE;
    }
}
