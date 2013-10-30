package com.sanli.swing;

import com.sanli.model.FCBean;

public class BatchEditPanel extends TablePanel{

	private static final long serialVersionUID = 1L;

	@Override
	public void doEdit(int id, int row) {
		FCBean bean = null;
		for(FCBean bb : beanList){
			if(bb.id == id){
				bean = bb;
				break;
			}
		}
		EditDialog2.getInstance().showEditDialog(bean, row);
	}

	@Override
	public void doDelete(int id, int row) {
//		boolean isDelete = AppController.getInstance().deleteOne(id);
//		if(isDelete){
//			AppWinUtils.showNormalMsg("É¾³ý[³É¹¦]!");
//		}else{
//			AppWinUtils.showWarnMsg("É¾³ý[Ê§°Ü]!");
//		}
		
//		List<FCBean> list = DataServer.getInstance().select(new FCBean());
		beanList.remove(id);
		showInTable(beanList);
	}

}
