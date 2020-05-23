package main.java.davidesk_cyphers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author davidesk
 */

 // Loaded by FXMLLoader internally not manually
@SuppressWarnings("unused")
public class FXMLBlockController {
        private String id;
	private Block block;
	private FlowPane pane;
	private BlockChain chain;
	
        //Constructors of FXMLBlockControl
	public FXMLBlockController(Block block, String id, BlockChain chain, FlowPane pane) {
		this.block = block;
		this.id = id;
		this.chain = chain;
		this.pane = pane;
	}

	@FXML
	private Label prevHash;
        
	@FXML
	private Label hash;

	@FXML
	private Label nonce;

	@FXML
	private Label time;

	@FXML
	private Label blockID;

	@FXML
	private Label data;

	@FXML
	private void initialize() {
		blockID.setText(id);
		hash.textProperty().bind(block.hashProperty());
		prevHash.textProperty().bind(block.previousHashProperty());
		Bindings.bindBidirectional(time.textProperty(), block.timeStampProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(nonce.textProperty(), block.nonceProperty(), new NumberStringConverter());
		data.textProperty().bind(block.dataProperty());
	}

	@FXML
	private void onEditDataClicked() {
		FXUtil.showInputDialog("New block data?").ifPresent(newData -> {
			block.setData(newData);
		});
	}

	@FXML
	private void onMineClicked() {
		if (chain.getBlockPos(block) > 0) {
			block.setPreviousHash(chain.getBlocks().get(chain.getBlockPos(block) - 1).getHash());
		}
		new Thread(() -> block.mineBlock(chain.getDifficulty())).start();
	}

	@FXML
	private void onDeleteClicked() {
		int pos = chain.getBlockPos(block);
		chain.getBlocks().remove(pos);
		pane.getChildren().remove(pos);
	}


}
