---
layout: default
title: Home
nav_order: 1
description: "Home - Create beautiful reports, right from Java!"
permalink: /
---

# Create beautiful reports, right from Java!

[Get started now](#getting-started){: .btn .btn-primary .fs-5 .mb-4 .mb-md-0 .mr-2 } [View it on GitHub](https://github.com/arkits/jaswand){: .btn .fs-5 .mb-4 .mb-md-0 }

---

## About

Jaswand is a library to generate HTML reports from within Java.

- Jaswand applies the concept of re-usable web components and offers a Java Library to generate HTML reports quickly.
- Jaswand makes use of the pre-existing data models from j2html to render HTML files.
- Jaswand MaterializeCSS 1.0 as the CSS framework.
- Jaswand's sourceCompatibility is 1.8.

## Getting Started

Jaswand is available via the [Central](https://mvnrepository.com/artifact/io.github.arkits/jaswand). Add it your project as a dependency:

```groovy
compile group: 'io.github.arkits', name: 'jaswand', version: '0.0.2'
```

## Usage

Basic Usage

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