package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MEETING;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.logic.commands.meeting.DeleteMeetingCommand;
import seedu.address.logic.commands.meeting.FindMeetingCommand;
import seedu.address.logic.commands.meeting.ListMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingContainsAllKeywordsPredicate;
import seedu.address.testutil.meeting.MeetingBuilder;
import seedu.address.testutil.meeting.MeetingUtil;

/**
 * Contains unit test for LinkyTimeParser
 * This file is an adapted version of AddressBook's AddressBookParser and is currently missing certain functions
 * due to missing implementation. Please refer to the obsolete package for reference.
 */
public class LinkyTimeParserTest {
    private final LinkyTimeParser parser = new LinkyTimeParser();

    @Test
    public void parseCommand_add() throws Exception {
        Meeting meeting = new MeetingBuilder().build();
        AddMeetingCommand command = (AddMeetingCommand) parser.parseCommand(MeetingUtil.getAddMeetingCommand(meeting));
        assertEquals(new AddMeetingCommand(meeting), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteMeetingCommand command = (DeleteMeetingCommand) parser.parseCommand(
                DeleteMeetingCommand.COMMAND_WORD + " " + INDEX_FIRST_MEETING.getOneBased());
        assertEquals(new DeleteMeetingCommand(INDEX_FIRST_MEETING), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        FindMeetingCommand command = (FindMeetingCommand) parser.parseCommand(
                FindMeetingCommand.COMMAND_WORD + " Tutorial");
        assertEquals(new FindMeetingCommand(new MeetingContainsAllKeywordsPredicate(List.of("Tutorial"))),
                command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListMeetingCommand.COMMAND_WORD) instanceof ListMeetingCommand);
        assertTrue(parser.parseCommand(ListMeetingCommand.COMMAND_WORD + " 3") instanceof ListMeetingCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
