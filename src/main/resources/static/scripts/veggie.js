(function (){
	
	vegTags = [];
	
	const vegId = document.querySelector('#veggieId').textContent;
	
	start();
	
	function start(){
		toggleTags();
		getVegTags();
		addVegTag();
		deleteVegTag();
		addRecipe();
		getRecipes();
	};
	
	function getVegTags(){
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(this.readyState == 4 &&thiws.status -- 200){
				vegTags = JSON.pars(xhr.response);
				vegTagListRender();
			}
		}
	}
	
	function toggleTags() {
		const showTagsButton
		const expandedTagView
		const veggieModal
		
		
		//if view tags button is clicked, toggle to expanded tag view. 
		//if tagname is clicked, show connected veggies in modal.
	}
	
})