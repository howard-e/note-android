package com.henterprise.note.utils;

import android.widget.EditText;

/**
 * @author Howard.
 */

public final class Validator {

    public static boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
