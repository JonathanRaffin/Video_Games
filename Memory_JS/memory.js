"use strict";

function debutpartie(){
    let boutton = document.getElementById('game');
    boutton.addEventListener('click',startMemoryGame);
}

function startMemoryGame(){
    let infoC = document.getElementById('infoC');
    let infoL = document.getElementById('infoL');
    let nbc = 0;
    let nbl = 0;  

    if (parseInt(infoL.value)%2 == 0 && parseInt(infoC.value)%2 == 0){
        nbl = parseInt(infoL.value);
        nbc = parseInt(infoC.value);
        jeu(nbl,nbc);
    }
    else{
        alert("Problèmes de dimension. Veuillez rentrer des nombres pairs.");
    }
}

function jeu(nbl,nbc){
    let info = document.querySelector('.nb');
    let coup = document.getElementById('coups');
    let score = document.getElementById('score');
    let paires = document.getElementById('paires');

    let espacejeu = document.getElementById('jeu');
    let dimjeuH = parseInt(window.getComputedStyle(espacejeu).getPropertyValue('height'));
    let dimjeuW = parseInt(window.getComputedStyle(espacejeu).getPropertyValue('width'));

    let imgretourner = [];
    let nbcartesuppr = 0;

    let tab = [];
    let long = nbc*nbl;
    let nbcartes = long/2;

    for (let i=1; i<=nbcartes; i++){
        let nbimg = Math.round(Math.random() * (32-1) + 1);
        tab.push(nbimg);
        tab.push(nbimg);
    }

    tab = melange(tab);

    for (let j=0; j<tab.length; j++){
        let div = document.createElement('div');
        let img = document.createElement('img');
        img.setAttribute('src',"images/js-logo.jpg");
        div.addEventListener('click',image);
        div.setAttribute('data-numero',j);
        div.appendChild(img);
        espacejeu.appendChild(div);
    }

    if (dimjeuW < dimjeuH){
        let taille = (dimjeuW) / nbc;
        espacejeu.style.gridTemplateColumns = "repeat("+nbc+","+taille+"px)";
        espacejeu.style.gridTemplateRows = "repeat("+nbc+","+taille+"px)";
    }else {
        let taille = (dimjeuH) / nbc;
        espacejeu.style.gridTemplateColumns = "repeat("+nbc+","+taille+"px)";
        espacejeu.style.gridTemplateRows = "repeat("+nbc+","+taille+"px)";
    }

    function image(event){
        if(imgretourner.length < 2){
            let obj = event.currentTarget;
            let fils = obj.childNodes[0] ;
            let position = parseInt(obj.dataset.numero);
            fils.setAttribute('src',"images/"+tab[position]+".jpg");
            imgretourner.push(obj);
            let img1 = imgretourner[0];
            let img2 = imgretourner[1];

            if (imgretourner.length == 2) {
                let compare1 = tab[img1.dataset.numero];
                let compare2 = tab[img2.dataset.numero];

                let newcoup = document.createElement('label');
                let txtcoup = document.createTextNode(parseInt(coup.textContent) + 1);
                newcoup.appendChild(txtcoup);
                info.insertBefore(newcoup,coup);
                info.removeChild(coup);
                coup = newcoup;

                if (compare1== compare2){
                    setTimeout(suppr,1000);
                    fingame();
                } 
                else{
                    setTimeout(retourner,1000);
                }   
            }

            let div1 = espacejeu.querySelector("div[data-numero='"+img1.dataset.numero+"']");
            let filsdiv1 = div1.childNodes[0];

            function suppr(event){
                obj.removeChild(fils);
                div1.removeChild(filsdiv1);
                obj.removeEventListener('click',image);
                div1.removeEventListener('click',image);
                imgretourner = [];
                nbcartesuppr += 2;

                let newscore = document.createElement('label');
                let txtscore = document.createTextNode(parseInt(score.textContent) + 3);
                newscore.appendChild(txtscore);
                info.insertBefore(newscore,score);
                info.removeChild(score);
                score = newscore;

                let newpaire = document.createElement('label');
                let txtpaire = document.createTextNode(parseInt(paires.textContent) + 1);
                newpaire.appendChild(txtpaire);
                info.insertBefore(newpaire,paires);
                info.removeChild(paires);
                paires = newpaire;
            }

            function retourner(){
                fils.setAttribute('src',"images/js-logo.jpg");
                filsdiv1.setAttribute('src',"images/js-logo.jpg");
                imgretourner = [];

                let newscore = document.createElement('label');
                let txtscore = document.createTextNode(parseInt(score.textContent) - 1);
                newscore.appendChild(txtscore);
                info.insertBefore(newscore,score);
                info.removeChild(score);
                score = newscore;
            }

            function fingame(){
                if(nbcartesuppr == long-2){
                    alert("Fin de la partie.");
                }
            }

        }

    }

}

function melange(listecarte){
    for (let i = listecarte.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (Math.floor(i) - Math.ceil(0) + 1)) + Math.ceil(0);
        [listecarte[i], listecarte[j]] = [listecarte[j], listecarte[i]];
    }
    return listecarte;
}

// Après plusieur recherches pour sauvegarder les parties, je n'ai pas réussi.