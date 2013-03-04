package com.comp5541.spreadsheet.tests;

import org.junit.runner.*;
import org.junit.runners.*;

/**
 * TestSuite for running all tests
 * @author Amy
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CellTest.class,
        ControllerTest.class,
        FileIOTest.class,
        SpreadsheetTest.class,
        SpreadsheetTableModelTest.class
})
public class TestSuite
{
}
