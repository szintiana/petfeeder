package com.backend.petfeeder.Service;

import java.io.*;

public class PythonExecutorService {
    public static String execute() throws IOException {
        Process p = Runtime.getRuntime().exec("python C:\\Users\\szint\\PycharmProjects\\testPetfeeder\\main.py");
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return in.readLine();
    }
}
