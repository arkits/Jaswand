---
layout: default
title: Your First Report
permalink: /first-report
nav_order: 2
---


## Your First Report


```java
// First create a Report object
Report report = new Report("Benchmark Report");

// and then export your Report as a HTML!
report.export("out/rendered.html");
```

Creating and Adding Elements

```java
// Create an ElementFactory
ElementFactory elementFactory = new ElementFactory();

// Generate a ReportCard element using the ElementFactory
ContainerTag card = elementFactory.reportCard(
    "Time Taken to Process", "20 ms");

// Add the card into your report
report.add(card);

```
