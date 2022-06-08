package com.backend.petfeeder.Utils;

import java.io.*;

public class PythonExecutorUtil {
    public static String execute(String path) throws IOException {
        Process p = Runtime.getRuntime().exec("python " + path);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String a = in.readLine();
        System.out.println(a);
        return a;
    }
}
