package graffiti;

import java.util.function.Consumer;

public class Text {

	private static final String COLORS = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
	private static final String NULL = String.valueOf(Character.MIN_VALUE);

	public static Text of(String text){
		return new Text(color(text));
	}

	private static String color(String text){
		char[] characters = text.toCharArray();

		for(int i = 0; i < characters.length - 1; i++){
			char color = characters[i + 1];

			if(characters[i] != '&' || COLORS.indexOf(color) <= -1) continue;

			if(i > 0 && characters[i - 1] == '-') characters[i - 1] = Character.MIN_VALUE;

			characters[i] = '§';
			characters[i + 1] = Character.toLowerCase(color);

			if(i < characters.length - 2 && characters[i + 2] == '-'){
				characters[i + 2] = Character.MIN_VALUE;
				i += 2;
			}else{
				i++;
			}
		}

		return text = new String(characters).replace(NULL, "");
	}

	private String text;

	public Text(String text){
		this.text = text;
	}

	public Text format(Object... objects){
		text = String.format(text, objects);
		return this;
	}

	public void accept(Consumer<String> action){
		action.accept(text);
	}

	@Override
	public String toString(){
		return text;
	}

}
