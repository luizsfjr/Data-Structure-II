#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int CPF;
    char Nome[50];
    char Profissao[30];
} Info;

typedef struct t_no {
    Info *info;
    struct t_no *esq;
    struct t_no *dir;
} No;

No *arvoreCPF;
No *arvoreNome;

// Cria um ponteiro da informacoes e retorna o endereco
Info *criaPtrInfo(char *nome, int cpf, char *profissao) {
    Info *i = (Info *)malloc(sizeof(Info));

    strcpy(i->Nome, nome);
    i->CPF = cpf;
    strcpy(i->Profissao, profissao);

    return i;
}

// Cria o No para ser inserido e retorna o endereço
No *criaNo(Info *info) {

    No *novo = (No *)malloc(sizeof(No));
    novo->info = info;
    novo->dir = NULL;
    novo->esq = NULL;

    return novo;
}

// Insere o No na arvore ordenada pelo CPF
No *insereArvoreCPF(No *raiz, No *novo) {
    if (raiz == NULL)
        return novo;
    if (raiz->info->CPF > novo->info->CPF)
        raiz->esq = insereArvoreCPF(raiz->esq, novo);
    else
        raiz->dir = insereArvoreCPF(raiz->dir, novo);
    return raiz;
}

// Insere o No na arvore de ordenada por Nome
No *insereArvoreNome(No *raiz, No *novo) {
    if (raiz == NULL)
        return novo;
    if (strcmp(raiz->info->Nome, novo->info->Nome) > 0)
        raiz->esq = insereArvoreNome(raiz->esq, novo);
    else
        raiz->dir = insereArvoreNome(raiz->dir, novo);
    return raiz;
}

// Insere os valores em ambas as avores
void insereArvore(char *nome, int cpf, char *profissao) {
    Info *i = criaPtrInfo(nome, cpf, profissao);

    arvoreCPF = insereArvoreCPF(arvoreCPF, criaNo(i));
    arvoreNome = insereArvoreNome(arvoreNome, criaNo(i));
}

void buscarCPF(int valor) {
    if (arvoreCPF == NULL) {
        printf("Sem Informacoes\n");
        return;
    }

    No *atual = arvoreCPF;

    while (atual != NULL) {
        if (valor == atual->info->CPF) {
            printf("%s ", atual->info->Nome);
            printf("%d ", atual->info->CPF);
            printf("%s\n", atual->info->Profissao);
            return;
        }
        if (valor > atual->info->CPF)
            atual = atual->dir;
        else
            atual = atual->esq;
    }
    if (atual == NULL)
        printf("0\n");
}
void buscarName(char *nome) {
    if (arvoreNome == NULL) {

        printf("Sem Informacoes\n");
        return;
    }

    No *atual = arvoreNome;

    while (atual != NULL) {
        if (strcmp(nome, atual->info->Nome) == 0) {
            printf("%s ", atual->info->Nome);
            printf("%d ", atual->info->CPF);
            printf("%s\n", atual->info->Profissao);
            return;
        }
        if (strcmp(nome, atual->info->Nome) > 0)
            atual = atual->dir;
        else
            atual = atual->esq;
    }
    if (atual == NULL)
        printf("0\n");
}

No *removeCpf(No *raiz, int cpf) {
    if (raiz == NULL)
        return NULL;
    else if (raiz->info->CPF > cpf)
        raiz->esq = removeCpf(raiz->esq, cpf);
    else if (raiz->info->CPF < cpf)
        raiz->dir = removeCpf(raiz->dir, cpf);
    else {
        if (raiz->esq == NULL && raiz->dir == NULL) {
            free(raiz);
            raiz = NULL;
        } else if (raiz->esq == NULL) {
            No *aux = raiz;
            raiz = raiz->dir;
            free(aux);
        } else if (raiz->dir == NULL) {
            No *aux = raiz;
            raiz = raiz->esq;
            free(aux);
        } else {
            No *f = raiz->esq;
            while (f->dir != NULL) {
                f = f->dir;
            }

            raiz->info = f->info;
            //f->info = v;
            raiz->esq = removeCpf(raiz->esq, cpf);
        }
    }
    return raiz;
}

No *removeNome(No *raiz, char *nome) {
    if (raiz == NULL)
        return NULL;
    else if (strcmp(raiz->info->Nome, nome) < 0)
        raiz->esq = removeNome(raiz->esq, nome);
    else if (strcmp(raiz->info->Nome, nome) > 0)
        raiz->dir = removeNome(raiz->dir, nome);
    else {
        if (raiz->esq == NULL && raiz->dir == NULL) {
            free(raiz);
            raiz = NULL;
        } else if (raiz->esq == NULL) {
            No *aux = raiz;
            raiz = raiz->dir;
            free(aux);
        } else if (raiz->dir == NULL) {
            No *aux = raiz;
            raiz = raiz->esq;
            free(aux);
        } else {
            No *f = raiz->esq;
            while (f->dir != NULL) {
                f = f->dir;
            }

            raiz->info = f->info;
            raiz->esq = removeNome(raiz->esq, nome);
        }
    }
    return raiz;
}

void *removeAll(char *nome, int cpf) {
    Info *i = NULL;
    if (nome == "" || nome == NULL) {
        No *atual = arvoreCPF;

        while (atual != NULL) {
            if (cpf == atual->info->CPF) {
                i = atual->info;
                break;
            }
            if (cpf > atual->info->CPF)
                atual = atual->dir;
            else
                atual = atual->esq;
        }
        arvoreCPF = removeCpf(arvoreCPF, cpf);
        arvoreNome = removeNome(arvoreNome, i->Nome);
    } else if (cpf == NULL) {
        No *atual = arvoreNome;

        while (atual != NULL) {
            if (strcmp(nome, atual->info->Nome) == 0) {
                i = atual->info;
                break;
            }
            if (strcmp(nome, atual->info->Nome) > 0)
                atual = atual->dir;
            else
                atual = atual->esq;
        }

        arvoreNome = removeNome(arvoreNome, nome);
        arvoreCPF = removeCpf(arvoreCPF, i->CPF);
    }

    free(i);
}

No *liberaArvore(No *tree) {
    if (tree != NULL) {
        liberaArvore(tree->esq);
        liberaArvore(tree->dir);
        free(tree);
    }

    return NULL;
}


void menu() {
    char nome[50];
    char profissao[30];
    char o, p[1];

    int cpf;

    while (1) {
        setbuf(stdin, NULL); // limpa o buffer do teclado
        scanf("%c", &o);

        switch (o) {
        case 'S':
            liberaArvore(arvoreCPF);
            liberaArvore(arvoreNome);
            return 0;
            break;
        case 'i':
            scanf("%s", nome);
            scanf("%d", &cpf);
            scanf("%s", profissao);

            insereArvore(nome, cpf, profissao);
            break;
        case 'b':
            scanf("%s", p);
            if (strcmp(p, "c") == 0) {
                scanf("%d", &cpf);
                buscarCPF(cpf);
            } else if (strcmp(p, "n") == 0) {
                scanf("%s", nome);
                buscarName(nome);
            }
            break;
        case 'r':
            scanf("%s", p);
            if (strcmp(p, "c") == 0) {
                scanf("%d", &cpf);
                removeAll(NULL, cpf);
            } else if (strcmp(p, "n") == 0) {
                scanf("%s", nome);
                removeAll(nome, NULL);
            }
            break;

        default:
            break;
        }
    }
}


int main() {

    arvoreCPF = NULL;
    arvoreNome = NULL;

    menu();
}

