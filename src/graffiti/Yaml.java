package graffiti;

import java.util.LinkedHashMap;
import java.util.Map;

public class Yaml {

	protected final Map<String, Object> map = new LinkedHashMap<>();
	private final Yaml parent, root;
	private String path, fullPath;

	//MemorySection > MemoryConfiguration > FileConfiguration > YamlConfiguration

}
