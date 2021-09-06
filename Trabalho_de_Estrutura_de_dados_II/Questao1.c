#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Árvore
struct Node{
    char info;
    struct Node *dir;
    struct Node *esq;
};

typedef struct Node No;

No* arv_vazia(void){
    return NULL;
}

No* cria_arvore(char i, No* esq, No* dir){
    No* p = (No*)malloc(sizeof(No));
    if(p==NULL){
        exit(1);
    }else{
        p->info = i;
        p->esq = esq;
        p->dir = dir;
    }
    return p;
}

void imprimePreOrdem(No* arvore){
    if(arvore!=NULL){
        printf("%c", arvore->info); // raiz
        imprimePreOrdem(arvore->esq); // subarvore da esquerda
        imprimePreOrdem(arvore->dir); // subarvore da direita
    }
}

void imprimeCentral(No* arvore){
    if(arvore!=NULL){
        imprimeCentral(arvore->esq);
        printf("%c", arvore->info);
        imprimeCentral(arvore->dir);
    }
}

void imprimePosOrdem(No* arvore){
    if(arvore!=NULL){
        imprimePosOrdem(arvore->esq);
        imprimePosOrdem(arvore->dir);
        printf("%c", arvore->info);
    }
}
int main()
{
    //lado esquerdo
    No* a1 = cria_arvore('H', arv_vazia(), arv_vazia());
    No* a2 = cria_arvore('E', a1, arv_vazia());
    No* a3 = cria_arvore('B', cria_arvore('D', arv_vazia(), arv_vazia()) , a2);

    //lado direito
    No *a4 = cria_arvore('I',arv_vazia(),arv_vazia());
    No *a5 = cria_arvore('J',arv_vazia(),arv_vazia());
    No *a6 = cria_arvore('F', a4, a5);
    No *a7 = cria_arvore('K',arv_vazia(),arv_vazia());
    No *a8 = cria_arvore('G',arv_vazia(),a7);
    No *a9 = cria_arvore('C', a6,a8);

    // raiz
    No *raiz = cria_arvore('A', a3, a9);

    //imprime árvore em percurso Central
    printf("Percurso centralizado: ");
    imprimeCentral(raiz);
    printf("\n");

    //Imprime árvore em percuro Pré-fixado
    printf("Percurso Pre-Fixado: ");
    imprimePreOrdem(raiz);
    printf("\n");

    //Imprime árvore em percurso Pós-fixado
    printf("Percurso Pos-Fixado: ");
    imprimePosOrdem(raiz);
    printf("\n");
    system("PAUSE");
    return 0;
}
