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
			if(this.readyState == 4 &&this.status == 200){
				vegTags = JSON.parse(xhr.response);
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
	
	function addVegTag(){
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){}
		// if button is clicked, open menu to add veg tag.
		//html form to select suitable veggies and name tag.

	}

	function deleteVegTag(){
		//removes tag from all veggies
	}

	function addRecipe(){

	}

	function getRecipes(){
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(this.readyState == 4 &&this.status == 200){
				vegTags = JSON.parse(xhr.response);
				vegTagListRender();
			}
		}
	}

})
