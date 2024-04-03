package com.goggleeyed.webapp.storage.serializer;

import com.goggleeyed.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                Class<? extends Section> cl = entry.getValue().getClass();
                dos.writeUTF(cl.toString());
                if (cl.equals(TextSection.class)) {
                    dos.writeUTF(entry.getValue().toString());
                } else if (cl.equals(ListSection.class)) {
                    List<String> items = ((ListSection) entry.getValue()).getItems();
                    dos.writeInt(items.size());
                    for (String item : items) {
                        dos.writeUTF(item);
                    }
                } else if (cl.equals(OrganizationSection.class)) {
                    List<Organization> organizations = ((OrganizationSection) entry.getValue()).getOrganizations();
                    dos.writeInt(organizations.size());
                    for (Organization organization : organizations) {
                        Link homePage = organization.getHomePage();
                        dos.writeUTF(homePage.getName());
                        dos.writeUTF(homePage.getUrl());
                        List<Organization.Position> positions = organization.getPositions();
                        dos.writeInt(positions.size());
                        for (Organization.Position position : positions) {
                            dos.writeUTF(position.getStartDate().toString());
                            dos.writeUTF(position.getEndDate().toString());
                            dos.writeUTF(position.getTitle());
                            dos.writeUTF(position.getDescription());
                        }
                    }
                }

            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                String cl = dis.readUTF();
                Section section = null;
                if (cl.equals(TextSection.class.toString())) {
                    section = new TextSection(dis.readUTF());
                } else if (cl.equals(ListSection.class.toString())) {
                    int itemsSize = dis.readInt();
                    List<String> items = new ArrayList<>(itemsSize);
                    for (int j = 0; j < itemsSize; j++) {
                        items.add(dis.readUTF());
                    }
                    section = new ListSection(items);
                } else if (cl.equals(OrganizationSection.class.toString())) {
                    int organizationsSize = dis.readInt();
                    List<Organization> organizations = new ArrayList<>(organizationsSize);
                    for (int j = 0; j < organizationsSize; j++) {
                        Link link = new Link(dis.readUTF(), dis.readUTF());
                        int positionsSize = dis.readInt();
                        List<Organization.Position> positions = new ArrayList<>(positionsSize);
                        for (int k = 0; k < positionsSize; k++) {
                            positions.add(new Organization.Position(LocalDate.parse(dis.readUTF()),
                                    LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF()));
                        }
                        organizations.add(new Organization(link, positions));
                    }
                    section = new OrganizationSection(organizations);
                }
                resume.addSection(sectionType, section);
            }
            return resume;
        }
    }
}