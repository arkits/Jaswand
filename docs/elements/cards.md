---
layout: default
title: Cards
parent: Elements
---

# Cards

[Demo](https://arkits.github.io/jaswand/samples/reportCards.html){: .btn .fs-5 .mb-4 .mb-md-0 }

Cards can be created from the `Element Factory`.

```java
elementFactory.reportCard(String cardTitle, String cardBody);
```

## Basic Sample

```java
// Create a Report
Report report = new Report("Report Cards Sample");

// Create an Element Factory
ElementFactory elementFactory new ElementFactory();

// Add a Card to your Report
report.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));

// Export your Report
report.export("samples/reportCards.html");
```

---