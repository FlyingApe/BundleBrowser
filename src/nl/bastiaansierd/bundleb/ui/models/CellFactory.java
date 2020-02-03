package nl.bastiaansierd.bundleb.ui.models;

import javafx.scene.SnapshotParameters;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Font;
import nl.bastiaansierd.bundleb.Main;
import nl.bastiaansierd.bundleb.enums.LeafType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.logic.objects.BundelBHeader;
import nl.bastiaansierd.bundleb.logic.objects.BundelTreeEditHolder;

// de CellFactory voor de TreeView
final class CellFactory extends TreeCell<BundelLeaf> {
    private BundelTreeView bundelTreeView;

    public CellFactory(BundelTreeView bundelTreeView) {
        this.bundelTreeView = bundelTreeView;
    }

    @Override
    protected void updateItem(BundelLeaf leaf, boolean empty){
        ImageView graphic = null;
        String text = null;

        super.updateItem(leaf, empty);

        double defaultFontSize = getFont().getSize();
        double microFontSize = 2;
        String placeHolder = "";

        if (!empty && leaf != null) {
            if(leaf.getLeafType() == LeafType.CATEGORY){
                setFont(new Font(defaultFontSize));
                graphic = new ImageView(new Image(Main.class.getResourceAsStream("images/chapter.png")));
                text = leaf.getName();
            } else if (leaf.getLeafType() == LeafType.PAGE){
                setFont(new Font(defaultFontSize));
                graphic = new ImageView(new Image(Main.class.getResourceAsStream("images/page.png")));
                text = leaf.getName();

                /*
                setOnMouseClicked(event -> {
                    //leaf omzetten naar header, zodat we de PageType kunnen achterhalen
                    Header header = (Header) leaf;
                    /*WebEngine webEngine = bundelTreeView.pageView.getEngine();
                    if(header.getPageType() == PageType.HTML){
                        try {
                            webEngine.load(header.getAddress());
                        } catch (Exception e) {
                            Logger.log("BundelTreeView.CellFactory.updateItem",e.getMessage());
                        }
                    }
                });*/

                setOnDragDetected(dragEvent -> {

                    Dragboard db = startDragAndDrop(TransferMode.MOVE);
                    /* Put a string on a dragboard
                     */
                    ClipboardContent content = new ClipboardContent();
                    content.putString(leaf.getName());
                    //System.out.println(((BundelBHeader) leaf).toString());

                    BundelTreeEditHolder.getInstance().setMovingLeaf(leaf);
                    BundelTreeEditHolder.getInstance().setParent(((BundelBHeader) leaf).getParent());

                    db.setContent(content);
                    db.setDragView(this.snapshot(new SnapshotParameters(),null));

                    setFont(new Font(0));
                    setText(placeHolder);
                    setGraphic(null);



                    dragEvent.consume();
                });
/*
                setOnDragDone(dragDoneEvent ->{
                    bundelTreeView.redraw();
                });
*/
            } else if (leaf.getLeafType() == LeafType.PLACEHOLDER){
                setFont(new Font(microFontSize));
                setText(placeHolder);
                setDisable(true);

                graphic = null;

                setOnDragOver(dragOverEvent -> {
                    if (dragOverEvent.getGestureSource() != this &&
                            dragOverEvent.getDragboard().hasString()) {
                        /* allow for moving */
                        dragOverEvent.acceptTransferModes(TransferMode.MOVE);
                    }

                    dragOverEvent.consume();

                    setFont(new Font(defaultFontSize));
                    this.setText(dragOverEvent.getDragboard().getString());
                });

                setOnDragExited(dragExitEvent -> {
                    setFont(new Font(microFontSize));
                    this.setText(placeHolder);
                });

                setOnDragDropped(dragDropEvent -> {
                    BundelTreeEditHolder.getInstance().setPlaceHolder((PlaceHolder) leaf);
                    BundelTreeEditHolder.getInstance().moveLeaf();
                    bundelTreeView.setTreeView();
                });
            }

            setGraphic(graphic);
            setText(text);
        } else {
            setText(null);
            setGraphic(null);
        }

    }
}
