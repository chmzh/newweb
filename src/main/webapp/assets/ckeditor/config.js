/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	// 图片上传配置
    config.filebrowserUploadUrl = 'upload/uploadfile?type=File';
    config.filebrowserImageUploadUrl = 'upload/uploadfile?type=Image';
    config.filebrowserFlashUploadUrl = 'upload/uploadfile?type=Flash';
};
