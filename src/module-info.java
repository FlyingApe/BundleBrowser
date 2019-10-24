module BundleBrowser {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.web;
    requires flexmark;
    requires flexmark.util;

    opens nl.bastiaansierd.bundleb.ui.controllers;
    opens nl.bastiaansierd.bundleb.ui.views;
    opens nl.bastiaansierd.bundleb.ui.models;
    opens nl.bastiaansierd.bundleb;
}