package com.goggleeyed.webapp.util;

import com.goggleeyed.webapp.model.Resume;
import com.goggleeyed.webapp.model.Section;
import com.goggleeyed.webapp.model.TextSection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.goggleeyed.webapp.ResumeTestData.RESUME_1;

class JsonParserTest {
    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assertions.assertEquals(RESUME_1, resume);
    }

    @Test
    public void write() throws Exception {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assertions.assertEquals(section1, section2);
    }
}