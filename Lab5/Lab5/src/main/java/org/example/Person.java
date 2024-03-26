package org.example;

import java.util.List;

record Person(String name, String id, List<Document> documents) {}