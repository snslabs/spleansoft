<?php
define('BRAND_SERVICE_URL' ,get_option('home') . '/wp-content/plugins/btc/brands-service.php');
define('CUR_PATH',dirname(__FILE__) . '/');
require_once(CUR_PATH . 'controls.php');
require_once(CUR_PATH . 'view.php');
require_once(CUR_PATH . 'velocity.php');
// managers
require_once(CUR_PATH . 'tours.php');
require_once(CUR_PATH . 'brands.php');


class AkmalPlugin{

    public $LANGUAGES = array("ru" => "Русский", "en" => "English", "uz" => "O'zbek");
    public $DEFAULT_LANG = "ru";

    private $tourManager;
    private $brandManager;
    private $companyManager;

    function AkmalPlugin(){
        $this->tourManager = new TourManager($this);
        $this->brandManager = new BrandManager($this);

        add_shortcode('tours_list',array (&$this->tourManager,  'tourListShortCodeHandler'));
        add_shortcode('brands_list',array (&$this->tourManager,  'brandListShortCodeHandler'));
        add_action('admin_menu',  array (&$this,  'admin') );
    }

    function getLangId(){
        // интеграция с WPML
        if(isset($_GET['lang'])){
            return $_GET['lang'];
        }
        else{
            return $this->DEFAULT_LANG;
        }
    }

    function admin(){
        add_menu_page('Общие настройки','Akmal', 10, "akmal", array (&$this,  'adminForm'));
        add_submenu_page("akmal",'Настройка туров','Настройка туров', 10, "tour",  array (&$this,  'toursAdminForm'));
        add_submenu_page("akmal",'Настройк брендов','Настройка брендов', 10, "brands",  array (&$this,  'brandsAdminForm'));
        add_submenu_page("akmal",'Настройк компаний','Настройка компаний', 10, "companies",  array (&$this,  'companiesAdminForm'));

    }

    function adminForm(){
        ?>
        <div class='wrap'>
            <div id='icon-options-general' class='icon32'><br></div>
            <h2>Общие настройки модуля бизнес-контента</h2>
        </div>
        <?php
    }

    function toursAdminForm(){
        return $this->tourManager->adminForm();
    }


    function brandsAdminForm(){
        return $this->brandManager->adminForm();
    }

    function companiesAdminForm(){
        return $this->companyManager->adminForm();
    }

    function getLanguages(){
        return $this->LANGUAGES;
    }


}
/* initialization */
global $akmalPlugin;
$akmalPlugin = new AkmalPlugin();


?>