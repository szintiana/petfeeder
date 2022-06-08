package com.backend.petfeeder.Service;

import com.backend.petfeeder.Utils.PythonExecutorUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HardwareService {
    public String sendMessage(String path) throws IOException {
        return PythonExecutorUtil.execute(path);
    }
}
