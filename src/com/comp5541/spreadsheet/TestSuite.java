package com.comp5541.spreadsheet;

import org.junit.runner.*;
import org.junit.runners.*;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CellTest.class,
        FileIOTest.class,
        SpreadsheetTest.class
})
public class TestSuite
{
}
