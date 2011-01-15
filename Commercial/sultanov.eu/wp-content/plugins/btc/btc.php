<?php
/*
Plugin Name: Tours
Plugin URI: http://www.snslabs.ru/wordpress/plugins/tours
Description: Displays touristic information
Version: 1.0.0
Author: SLyubimov
Author URI: http://www.snslabs.ru/
*/
/*  Copyright 2010  snslabs

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

global $wpdb;

define('WP_TOURS_FILE_PATH', dirname(__FILE__));

include_once(WP_TOURS_FILE_PATH."/install.php");
register_activation_hook(__FILE__, 'activateToursPlugin' );
include_once(WP_TOURS_FILE_PATH . "/akmal.php");
?>