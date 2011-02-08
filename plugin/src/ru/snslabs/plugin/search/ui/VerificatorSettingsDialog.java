package ru.snslabs.plugin.search.ui;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import ru.snslabs.plugin.search.scanner.impl.RegexpFilter;
import ru.snslabs.plugin.search.scanner.impl.RegexpScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VerificatorSettingsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList fileTypes;
    private JButton addScannerButton;
    private JButton deleteScannerButton;
    private JPanel scannersPanel;
    private boolean result = false;
    private java.util.List<ScannerUI> scannerList = new ArrayList<ScannerUI>();
    private java.util.List<FileType> acceptedFileTypes = new ArrayList<FileType>();

    public VerificatorSettingsDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        addScannerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addScanner();
            }
        });
        deleteScannerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                deleteScanner();
            }
        });

        final List<FileTypeUI> ftUI = new ArrayList<FileTypeUI>();
        for (FileType fileType : FileTypeManager.getInstance().getRegisteredFileTypes()) {
            ftUI.add(new FileTypeUI(fileType));
        }
        fileTypes.setModel(new AbstractListModel() {
            public int getSize() {
                return ftUI.size();
            }
            public Object getElementAt(int i) {
                return ftUI.get(i);
            }

        });

    }

    private void deleteScanner() {

        removeScanners();
        if(scannerList.size()>0){
            scannerList.remove(scannerList.size()-1);
        }
        placeScanners();
    }

    private void addScanner() {
        removeScanners();
        scannerList.add(new ScannerUI());
        placeScanners();
    }

    private void placeScanners() {

        for(int index = 0; index < scannerList.size(); index++){
            ScannerUI scannerUI = scannerList.get(index);
            scannersPanel.add(scannerUI.getMainPanel(),new GBC(0,index).setWeight(1,(index==scannerList.size()-1)?1:0).
                    setAnchor(GridBagConstraints.NORTH).setFill(GridBagConstraints.HORIZONTAL) );
            System.out.println("index="+index);
        }
        scannersPanel.updateUI();
    }

    private void removeScanners() {
        for(ScannerUI scannerUi :scannerList){
            scannersPanel.remove(scannerUi.getMainPanel());
        }
    }

    private void onOK() {
// add your code here if necessary
        // selected file types
        for (int i = 0; i < fileTypes.getSelectedValues().length; i++) {
            FileType fileType = ((FileTypeUI) fileTypes.getSelectedValues()[i]).getFt();
            acceptedFileTypes.add(fileType);
        }
        result = true;
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        result = false;
        dispose();
    }

    public static void main(String[] args) {
        VerificatorSettingsDialog dialog = new VerificatorSettingsDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public List<RegexpScanner> getRegexpScanners() {

        List<RegexpScanner> list = new ArrayList<RegexpScanner>();
        for(ScannerUI scannerForm : scannerList){
            String regexp1 = scannerForm.getRegexp();
            final RegexpScanner scanner = new RegexpScanner("Regexp Scanner "+regexp1);
            scanner.setRegexp(regexp1);

            for(FilterForm ff : scannerForm.getFilterForms()){
                if(ff.getFilterType().equals("Regexp")){
                    RegexpFilter filter = new RegexpFilter();
                    filter.setPattern(ff.getParameter());
                    scanner.getFilters().add(filter);
                }
            }


            list.add(scanner);
        }
        return list;
    }

    public boolean getResult() {
        return result;
    }

    public List<FileType> getAcceptedFileTypes(){
        return acceptedFileTypes;
    }

    class FileTypeUI {

        private FileType ft;

        public FileTypeUI(FileType ft){
            this.ft = ft;
        }

        public String toString() {
            return ft.getName();
        }

        public FileType getFt() {
            return ft;
        }
    }
}
