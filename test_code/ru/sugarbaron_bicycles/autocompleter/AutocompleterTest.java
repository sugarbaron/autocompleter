package ru.sugarbaron_bicycles.autocompleter;

import org.junit.Test;

public final class AutocompleterTest {
    @Test
    public void main_inputFileCase() {
        String[] arguments = new String[1];
        arguments[0] = "positiveCase.in";
        Autocompleter.main(arguments);
        return;
    }

    @Test
    public void main_twoArgumentsCase() {
        String[] arguments = new String[2];
        arguments[0] = "positiveCase.in";
        arguments[1] = "something";
        Autocompleter.main(arguments);
        return;
    }

    @Test
    public void main_threeArgumentsCase() {
        String[] arguments = new String[3];
        arguments[0] = "positiveCase.in";
        arguments[1] = "something";
        arguments[2] = "something_another";
        Autocompleter.main(arguments);
        return;
    }

    @Test
    public void main_wrongFileCase() {
        String[] arguments = new String[1];
        arguments[0] = "wrongFileName.in";
        Autocompleter.main(arguments);
        return;
    }
}