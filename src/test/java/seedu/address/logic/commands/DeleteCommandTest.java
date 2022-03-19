package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingEntryAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING_ENTRY;
import static seedu.address.testutil.TypicalMeetingEntries.getTypicalLinkyTime;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetingentry.DeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meetingentry.MeetingEntry;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalLinkyTime());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        final MeetingEntry meetingEntryToDelete = model.getFilteredMeetingEntryList()
                .get(INDEX_FIRST_MEETING_ENTRY.getZeroBased());
        final DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MEETING_ENTRY);

        final String expectedMessage =
                String.format(DeleteCommand.MESSAGE_SUCCESS, meetingEntryToDelete);

        final ModelManager expectedModel =
                new ModelManager(getTypicalAddressBook(), new UserPrefs(), model.getLinkyTime());
        expectedModel.deleteMeetingEntry(meetingEntryToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEETING_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingEntryAtIndex(model, INDEX_FIRST_MEETING_ENTRY);

        final MeetingEntry meetingEntryToDelete = model.getFilteredMeetingEntryList()
                .get(INDEX_FIRST_MEETING_ENTRY.getZeroBased());
        final DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MEETING_ENTRY);

        final String expectedMessage =
                String.format(DeleteCommand.MESSAGE_SUCCESS, meetingEntryToDelete);

        final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), model.getLinkyTime());
        expectedModel.deleteMeetingEntry(meetingEntryToDelete);
        showNoMeetingEntry(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingEntryAtIndex(model, INDEX_FIRST_MEETING_ENTRY);

        final Index outOfBoundIndex = INDEX_SECOND_MEETING_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLinkyTime().getMeetingEntryList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEETING_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_MEETING_ENTRY);
        final DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_MEETING_ENTRY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        final DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_MEETING_ENTRY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different meeting entry -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }


    /**
     * Updates {@code model}'s filtered list to show no meeting entries.
     */
    private void showNoMeetingEntry(Model model) {
        model.updateFilteredMeetingEntryList(p -> false);

        assertTrue(model.getFilteredMeetingEntryList().isEmpty());
    }
}
