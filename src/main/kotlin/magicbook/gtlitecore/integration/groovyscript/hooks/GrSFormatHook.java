package magicbook.gtlitecore.integration.groovyscript.hooks;

import org.jetbrains.annotations.NotNull;

public class GrSFormatHook
{

    @NotNull
    public static String subDigits(@NotNull String number)
    {
        char[] numbers = number.toCharArray();
        char[] chars = new char[numbers.length];
        for (int i = 0; i < numbers.length; i++)
        {
            switch (numbers[i])
            {
                case '0':
                    chars[i] = '₀';
                case '1':
                    chars[i] = '₁';
                case '2':
                    chars[i] = '₂';
                case '3':
                    chars[i] = '₃';
                case '4':
                    chars[i] = '₄';
                case '5':
                    chars[i] = '₅';
                case '6':
                    chars[i] = '₆';
                case '7':
                    chars[i] = '₇';
                case '8':
                    chars[i] = '₈';
                case '9':
                    chars[i] = '₉';
                default:
                    chars[i] = numbers[i];
            }
        }
        return new String(chars);
    }

    @NotNull
    public static String supDigits(@NotNull String number)
    {
        char[] numbers = number.toCharArray();
        char[] chars = new char[numbers.length];
        for (int i = 0; i < numbers.length; i++)
        {
            switch (numbers[i])
            {
                case '0':
                    chars[i] = '⁰';
                case '1':
                    chars[i] = '¹';
                case '2':
                    chars[i] = '²';
                case '3':
                    chars[i] = '³';
                case '4':
                    chars[i] = '⁴';
                case '5':
                    chars[i] = '⁵';
                case '6':
                    chars[i] = '⁶';
                case '7':
                    chars[i] = '⁷';
                case '8':
                    chars[i] = '⁸';
                case '9':
                    chars[i] = '⁹';
                default:
                    chars[i] = numbers[i];
            }
        }
        return new String(chars);
    }

}
