<h1 align="center"> LibRadioactive </h1>
<p align="center"> A lib to create radioactive items, blocks and contaminated areas </p>
<p align="center"> And now you can create pollution blocks and areas (above 0.1.2) </p>
<p align="center">
    <a href="https://opensource.org/licenses/Apache-2.0"><img src="https://img.shields.io/badge/License-Apache%202.0-brightgreen.svg"></a>
    <a href="https://github.com/Rochedo098/LibRadioactive/">  <img src="http://cf.way2muchnoise.eu/versions/391708_latest.svg"> </a>
</p>

### Some Information's
A large part of the LibRadioactive API is based on [CottonMC's LibPolution API](https://github.com/CottonMC/LibPollution)  
Thanks for that

## Importing the API

```groovy
/* File: build.gradle */
repositories {
    maven {
        name = "Rochedo098"
        url = "https://github.com/Rochedo098/rochedo098.github.io/tree/master/maven"
        content {
            includeGroup "com.github.rochedo098"
        }
    }   
}

dependencies {
    // Using gradle.properties
    modImplementation include("com.github.rochedo098:LibRadioactive:${project.libradioactive_version}")
    
    // Without gradle.properties
    modImplementation include("com.github.rochedo098:LibRadioactive:0.1.1")
}
```

```properties
## File: gradle.properties ##
libradioactive_version = "0.1.1"
```

## Some useful codes

### Creating a Radioactive Item

```java
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.github.rochedo098.libradioactive.api.item.RadioactiveItem;

public class Mod implements ModInitializer {
    @Override
    public void onInitialize() {
        public final Item RADIOACTIVE_ITEM = RadioactiveItem(new Item.Settings(), 1f, 4);
        Registry.register(Registry.ITEM, new Identifier("tutorial", "radioactive_item"), RADIOACTIVE_ITEM);
    }
}
```

### Creating a Radioactive Block

```java
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.github.rochedo098.libradioactive.api.block.RadioactiveBlock;

public class Mod implements ModInitializer {
    @Override
    public void onInitialize() {
        public final Block RADIOACTIVE_BLOCK = RadioactiveBlock(new AbstractBlock.Settings.of(Material.METAL), new Item.Settings(), 1f, 2, 3);
        Registry.register(Registry.BLOCK, new Identifier("tutorial", "radioactive_block"), RADIOACTIVE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("tutorial", "radioactive_block"), BlockItem(RADIOACTIVE_BLOCK, new Item.Settings()));
    }
}
```

### Creating General Radioactive Area

```java
import net.fabricmc.api.ModInitializer;
import com.github.rochedo098.libradioactive.api.RadiationArea;
import com.github.rochedo098.libradioactive.api.SimpleRadiationType;


public class Mod implements ModInitializer {
    @Override
    public void onInitialize() {
        RadioactiveArea.radiate(new SimpleRadiationType(1f, 3), 3);
    }
}
```

### Creating General Radioactive Area
##### This code probably not working

```java
import net.fabricmc.api.ModInitializer;
import com.github.rochedo098.libradioactive.impl.RadioationState;

public class Mod implements ModInitializer {
    @Override
    public void onInitialize() {
        RadiationState.setRadiation(chunkPos, null);
    }
}
```

## Building From Source

### Linux

Run `./gradlew build`, the compiled jar will be in the `/build/libs` folder.  
You must obtain the file without -dev and -sources.

### Windows

Run `gradlew.bat build`, the compiled jar will be in the `/build/libs` folder.  
You must obtain the file without -dev and -sources.

## License

This project is available under the Apache License, Version 2.0. See `LICENSE` for more information
