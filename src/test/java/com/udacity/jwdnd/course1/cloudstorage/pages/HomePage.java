package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public boolean isPageDisplayed() {
        return isElementDisplayed(By.id("logoutButton"))
                && isElementDisplayed(By.id("nav-files-tab"))
                && isElementDisplayed(By.id("nav-notes-tab"))
                && isElementDisplayed(By.id("nav-credentials-tab"));
    }

    @Override
    WebDriver getWebDriver() {
        return webDriver;
    }

    public void logout() {
        click("logoutButton");
    }

    public void clickOnNotesTab() {
        click("nav-notes-tab");
    }

    public void addNote(Note note) {
        click("addNewNoteButton");

        setInputText(note.getNoteTitle(), "note-title");
        setInputText(note.getNoteDescription(), "note-description");

        click("noteSubmit");
    }

    public void updateNote(Note note) {
        setInputText(note.getNoteTitle(), "note-title");
        setInputText(note.getNoteDescription(), "note-description");

        click("noteSubmit");
    }

    public void clickOnEditNoteButton() {
        click("editNoteButton");
    }

    public Note getFirstNote() {
        return new Note(
                getInputText("note-title"),
                getInputText("note-description")
        );
    }

    public void deleteFirstNote() {
        click("deleteNoteButton");
    }

    public boolean isFirstNoteDisplayed() {
        // If the Edit or the Delete buttons are present,
        // this means the note itself is present.
        return isElementDisplayed(By.id("editNoteButton"))
                || isElementDisplayed(By.id("deleteNoteButton"));
    }
}
