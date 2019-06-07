# ColorTextView
AppCompatTextView with customizable background and second text

# Get Started 

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Step 2. Add the dependency

dependencies {

	        implementation 'com.github.Juxtlie:ColorTextView:Tag'
	}


Available attrs 

        <attr name="ctv_second_font" format="reference"/>
        <attr name="ctv_padding_start" format="integer"/>
        <attr name="ctv_color" format="color"/>
        <attr name="ctv_padding_vertical" format="integer"/>
        <attr name="ctv_second_text" format="string"/>
        <attr name="ctv_second_text_size" format="integer"/>
        <attr name="ctv_all_caps" format="boolean"/>
        <attr name="ctv_strikethrough_mode" format="enum">
            <enum name="none" value="-1"/>
            <enum name="all" value="0" />
            <enum name="first" value="1" />
            <enum name="second" value="2"/>
        </attr>
