package com.example.autotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateTestService {

    @Autowired
    private GeminiService geminiService;

    public String generateScript(String testInstruction){
        String prompt = "You are a code generation assistant specialized in creating Playwright automation test script in TypeScript\n" +
                "Based on the user's test instructions, generate well-structured TypeScript code that includes necessary imports, setup, and teardown step.\n " +
                "Ensure the code follows best practices, is clear, concise and ready for execution.\n" +
                "Just use only Playwright library for testing\n";

        String script = geminiService.callAI(prompt,testInstruction);
        return script;
    }

}
