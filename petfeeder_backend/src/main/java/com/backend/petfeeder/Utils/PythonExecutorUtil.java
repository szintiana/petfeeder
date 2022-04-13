package com.backend.petfeeder.Utils;

import java.io.*;

public class PythonExecutorUtil {
    public static String execute() throws IOException {
        Process p = Runtime.getRuntime().exec("python C:\\Users\\szint\\PycharmProjects\\testPetfeeder\\main.py");
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return in.readLine();
    }
}
