//package seedu.address.logic.parser;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_PERSON;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.logic.commands.ClearCommand;
//import seedu.address.logic.commands.ExitCommand;
//import seedu.address.logic.commands.HelpCommand;
//import seedu.address.logic.commands.person.AddMeetingCommand;
//import seedu.address.logic.commands.person.DeleteMeetingCommand;
//import seedu.address.logic.commands.person.EditMeetingCommand;
//import seedu.address.logic.commands.person.EditMeetingCommand.EditPersonDescriptor;
//import seedu.address.logic.commands.person.FindMeetingCommand;
//import seedu.address.logic.commands.person.ListMeetingCommand;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.person.NameContainsKeywordsPredicate;
//import seedu.address.model.person.Person;
//import seedu.address.testutil.EditPersonDescriptorBuilder;
//import seedu.address.testutil.PersonBuilder;
//import seedu.address.testutil.PersonUtil;
//
//public class AddressBookParserTest {
//
//    private final AddressBookParser parser = new AddressBookParser();
//
//    @Test
//    public void parseCommand_add() throws Exception {
//        Person person = new PersonBuilder().build();
//        AddMeetingCommand command = (AddMeetingCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
//        assertEquals(new AddMeetingCommand(person), command);
//    }
//
//    @Test
//    public void parseCommand_clear() throws Exception {
//        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
//        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
//    }
//
//    @Test
//    public void parseCommand_delete() throws Exception {
//        DeleteMeetingCommand command = (DeleteMeetingCommand) parser.parseCommand(
//                DeleteMeetingCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
//        assertEquals(new DeleteMeetingCommand(INDEX_FIRST_PERSON), command);
//    }
//
//    @Test
//    public void parseCommand_edit() throws Exception {
//        Person person = new PersonBuilder().build();
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
//        EditMeetingCommand command = (EditMeetingCommand) parser.parseCommand(EditMeetingCommand.COMMAND_WORD + " "
//                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
//        assertEquals(new EditMeetingCommand(INDEX_FIRST_PERSON, descriptor), command);
//    }
//
//    @Test
//    public void parseCommand_exit() throws Exception {
//        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
//        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
//    }
//
//    @Test
//    public void parseCommand_find() throws Exception {
//        List<String> keywords = Arrays.asList("foo", "bar", "baz");
//        FindMeetingCommand command = (FindMeetingCommand) parser.parseCommand(
//                FindMeetingCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
//        assertEquals(new FindMeetingCommand(new NameContainsKeywordsPredicate(keywords)), command);
//    }
//
//    @Test
//    public void parseCommand_help() throws Exception {
//        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
//        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
//    }
//
//    @Test
//    public void parseCommand_list() throws Exception {
//        assertTrue(parser.parseCommand(ListMeetingCommand.COMMAND_WORD) instanceof ListMeetingCommand);
//        assertTrue(parser.parseCommand(ListMeetingCommand.COMMAND_WORD + " 3") instanceof ListMeetingCommand);
//    }
//
//    @Test
//    public void parseCommand_unrecognisedInput_throwsParseException() {
//        assertThrows(ParseException.class,
//        String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
//            -> parser.parseCommand(""));
//    }
//
//    @Test
//    public void parseCommand_unknownCommand_throwsParseException() {
//        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
//    }
//}
