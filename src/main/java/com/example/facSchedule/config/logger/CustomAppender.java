package com.example.facSchedule.config.logger;


import java.io.*;

import ch.qos.logback.classic.spi.ILoggingEvent;

import ch.qos.logback.core.AppenderBase;



public class CustomAppender extends AppenderBase<ILoggingEvent> {

    private String prefix;

    private final File file = new File("./logs/my-custom-logs.log");

    @Override
    protected void append(final ILoggingEvent event) {
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(file, true));
            out
                    .append(prefix)
                    .append(' ')
                    .append(String.valueOf(System.currentTimeMillis()))
                    .append(' ')
                    .append(event.toString())
                    .append('\n');
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

}