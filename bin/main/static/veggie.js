	
let vegTags = [ ];
	
	const vegId = document.querySelector('#veggieId').textContent;
	
	start();
	
	function start(){
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
				vegTagsRender(vegTags);
			}
		}
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


	function addRecipe(event){
		event.preventDefault();

        let recipeName = document.querySelector("[name=recipeName]").value;
        let veggieName = document.querySelector("[name=veggieName]").value;

		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(this.readyState == 4 &&this.status == 200){
				vegTags = JSON.parse(xhr.response);
				recipeRender();
			}
		};
		xhr.open("POST", `/newRecipe/${recipeName}/${veggieName}`);
		xhr.send();
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

