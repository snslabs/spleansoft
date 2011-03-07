=== WP Easy Uploader ===
Contributors: chrisbliss18
Donate link: http://gaarai.com/donations/
Tags: upload, plugins, themes, ftp, archives, files, plugin, wordpress mu
Requires at least: 2.2
Tested up to: 2.6
Stable tag: 1.0.7b

Easily upload any type of content without the need for FTP. You can even upload plugin and theme archives, and the files will be extracted for you.

== Description ==

__WP Easy Uploader__ was created to make the life of WordPress bloggers easier by allowing an Administrator to add plugins, themes, and any type of file to WordPress directly from inside WordPress. You no longer have to load up an FTP client just to try out that latest plugin or to upload a text document for people to download.

= Current Features =

* Upload any type of file to either the standard Uploads directory (just as images do when you are creating posts and pages) or to a path of your choosing.
* Manual paths are automatically created if they don't exist.
* Easily upload plugins directly to the plugins directory and theme files directly to the themes directory.
* You can select to have archives automatically extracted to their destination. This means that plugins and themes will be taken care of for you, but it also means that you can zip up a large set of files and upload them quickly into a folder on your site. Currently zip, tar, gz, tar.gz, tgz, and tar.bz2 archives are supported.
* Options for overwriting existing files, renaming the fle if a file with that name already exists (or if overwriting fails), removal of the archive after file extraction, and forcing extracted files to be contained in a folder.
* Now supports WordPress Mu.
* Now supports internationalization. The PO file is available in the lang directory. Currently, Turkish (tr), mainland Chinese (zhCN), Brazilian Portuguese (ptBR), German (deDE), Indonesian (idID), and Spanish (esES) translations are available.

= Planned Features =

* Customizable permissions to allow for users with privileges below Administrator to upload files. My idea is for Administrators to have full access to all the uploader's functionality while people below Administrator won't have access to upload themes, plugins, or to manual paths and won't be able to overwrite files or extract archives.
* If the above is implemented, I'd like to provide a menu of options for tailoring what minimum access level is required to access each function.
* Also if the first item is implemented, I'd like to add logging in order to keep track of who uploaded what and when. This would be useful for keeping track of anyone (such as a contributor) who is using the file space inappropriately.

== Installation ==

1. Download and unzip the latest release zip file
1. Upload the entire wp-easy-uploader directory to your `/wp-content/plugins/` directory
1. Activate the plugin through the 'Plugins' menu in WordPress

= Using =

1. Activate the plugin
1. Click on "Manage"
1. Click on "Upload Files"

== Screenshots ==

1. The Upload Files page.

== Requirements ==

* PHP 4+
* WordPress 2.2+

== Version History ==

* 1.0.1 - 2008-06-27 - Initial release version
* 1.0.2 - 2008-06-28 - Updated to support WordPress Mu
* 1.0.3 - 2008-06-30 - Added support for PHP 4
* 1.0.4 - 2008-07-02 - Fixed compatibility bug with the BackUpWordPress plugin
* 1.0.5 - 2008-07-02 - Internationalized the plugin. Now it's ready for translators.
* 1.0.6 - 2008-07-06 - Added Turkish and Spanish translations. Fixed compatibility issue with the WeatherIcon plugin. Fixed parsing error with Windows servers.
* 1.0.7 - 2008-07-08 - Added mainland Chinese and Brazilian Portuguese translations. Added 2.6 support.
* 1.0.7a - 2008-07-10 - Added German translation.
* 1.0.7b - 2008-07-21 - Added Indonesian translation.


== More Information ==

For more information about this plugin and its development, visit the [WP Easy Uploader Home Page](http://gaarai.com/wp-easy-uploader/ "wp easy uploader home page").
