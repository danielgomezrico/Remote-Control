package com.makingiants.model;

import android.view.KeyEvent;

public class KeyboardManager {
	
	public static String getValueToSend(int keyCode) {
		
		if (keyCode == KeyEvent.KEYCODE_DEL) {
			return "BACK_SPACE";
			
		} else if (keyCode == KeyEvent.KEYCODE_ENTER) {
			return "ENTER";
			
		} else if (keyCode == KeyEvent.KEYCODE_TAB) {
			return "TAB";
			
		} else if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT) {
			return "CAPS";
			
		} else if (keyCode == KeyEvent.KEYCODE_MINUS) {
			return "-";
			
		} else if (keyCode == KeyEvent.KEYCODE_PLUS) {
			return "+";
			
		} else if (keyCode == KeyEvent.KEYCODE_PERIOD) {
			return ".";
		} else {
			return null;
		}
	}
	
	public static String getValueToSend(char letter) {
		
		/*if (letter == ',') {
			return "COMMA";
		}
		else*//*if (letter == '-') {
		      return "MINUS";
		      }
		      else if (letter == '.') {
		      return "PERIOD";
		      }
		      else if (letter == '/') {
		      return "SLASH";
		      }
		      else if (letter == '[') {
		      return "OPEN_BRACKET";
		      }
		      else if (letter == '\\') {
		      return "BACK_SLASH";
		      }
		      else if (letter == ']') {
		      return "CLOSE_BRACKET";
		      }
		      else if (letter == '*') {
		      return "MULTIPLY";
		      }
		      else if (letter == '*') {
		      return "PLUS";
		      }
		      else if (letter == '\"') {
		      return "QUOTE";
		      }
		      else if (letter == '&') {
		      return "AMPERSAND";
		      }
		      else if (letter == '<') {
		      return "LESS";
		      }
		      else if (letter == '>') {
		      return "GREATER";
		      }
		      else if (letter == ':') {
		      return "COLON";
		      }
		      else if (letter == ';') {
		      return "SEMICOLON";
		      }
		      else if (letter == '^') {
		      return "CIRCUMFLEX";
		      }
		      else if (letter == '$') {
		      return "DOLLAR";
		      }
		      else if (letter == 'E') {
		      return "EURO_SIGN";
		      }
		      else if (letter == '!') {
		      return "EXCLAMATION_MARK";
		      }
		      else if (letter == 'Á') {
		      return "INVERTED_EXCLAMATION_MARK";
		      }
		      else if (letter == '(') {
		      return "LEFT_PARENTHESIS";
		      }
		      else if (letter == '#') {
		      return "NUMBER_SIGN";
		      }
		      else if (letter == ')') {
		      return "RIGHT_PARENTHESIS";
		      }
		      else if (letter == '_') {
		      return "UNDERSCORE";
		      }
		      else {*/
		if (letter == ' ') {
			return "SPACE";
		} else {
			return "" + letter;
		}
		//}
	}
}
