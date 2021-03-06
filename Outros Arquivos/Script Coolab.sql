SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema coolab
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `coolab` DEFAULT CHARACTER SET utf8 ;
USE `coolab` ;

-- -----------------------------------------------------
-- Table `coolab`.`Categoria_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Categoria_Produto` (
  `id_categoria` INT(11) NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(100) NULL DEFAULT NULL,
  `situacao` VARCHAR(10) NULL DEFAULT NULL,
  `data_cad_categoria` DATE NULL,
  `data_ultima_alteracao_categoria` DATE NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Curso` (
  `id_curso` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_curso` VARCHAR(100) NULL DEFAULT NULL,
  `abrev_curso` VARCHAR(10) NULL DEFAULT NULL,
  `situacao_curso` VARCHAR(10) NULL,
  `data_cad_curso` DATE NULL,
  `data_ultima_alteracao_curso` DATE NULL,
  PRIMARY KEY (`id_curso`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Entrada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Entrada` (
  `id_entrada` INT(11) NOT NULL AUTO_INCREMENT,
  `data_entrada` DATE NULL DEFAULT NULL,
  `descricao_entrada` VARCHAR(500) NULL,
  `situacao_entrada` VARCHAR(50) NULL,
  `data_alteracao_entrada` DATE NULL,
  `observacao_entrada` VARCHAR(500) NULL,
  PRIMARY KEY (`id_entrada`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Produto` (
  `id_produto` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NULL DEFAULT NULL,
  `data_cad_produto` DATE NULL DEFAULT NULL,
  `data_ultima_alteracao_produto` DATE NULL DEFAULT NULL,
  `quantidade_minima` INT(11) NULL DEFAULT NULL,
  `unidade` VARCHAR(5) NULL DEFAULT NULL,
  `macro` VARCHAR(10) NULL DEFAULT NULL,
  `quantidade_macro` INT(11) NULL DEFAULT NULL,
  `situacao` VARCHAR(10) NULL DEFAULT NULL,
  `identificador` VARCHAR(50) NULL DEFAULT NULL,
  `solicita_lote` VARCHAR(10) NULL DEFAULT NULL,
  `solicita_devolucao` VARCHAR(10) NULL DEFAULT NULL,
  `valor` DOUBLE NULL DEFAULT NULL,
  `Categoria_Produto_id_categoria` INT(11) NOT NULL,
  PRIMARY KEY (`id_produto`),
  INDEX `fk_Produto_Categoria_Produto1_idx` (`Categoria_Produto_id_categoria` ASC),
  CONSTRAINT `fk_Produto_Categoria_Produto1`
    FOREIGN KEY (`Categoria_Produto_id_categoria`)
    REFERENCES `coolab`.`Categoria_Produto` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Entrada_Itens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Entrada_Itens` (
  `id_entrada_itens` INT(11) NOT NULL AUTO_INCREMENT,
  `quantidade` DOUBLE NULL DEFAULT NULL,
  `lote` VARCHAR(50) NULL DEFAULT NULL,
  `data_validade` DATE NULL DEFAULT NULL,
  `preco` DOUBLE NULL,
  `data_entrada_produto` DATE NULL,
  `Produto_id_produto` INT(11) NOT NULL,
  `Entrada_id_entrada` INT(11) NOT NULL,
  PRIMARY KEY (`id_entrada_itens`),
  INDEX `fk_Entrada_Itens_Produto2_idx` (`Produto_id_produto` ASC),
  INDEX `fk_Entrada_Itens_Entrada1_idx` (`Entrada_id_entrada` ASC),
  CONSTRAINT `fk_Entrada_Itens_Entrada1`
    FOREIGN KEY (`Entrada_id_entrada`)
    REFERENCES `coolab`.`Entrada` (`id_entrada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Entrada_Itens_Produto2`
    FOREIGN KEY (`Produto_id_produto`)
    REFERENCES `coolab`.`Produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Usuario` (
  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL DEFAULT NULL,
  `login` VARCHAR(50) NULL DEFAULT NULL,
  `senha` VARCHAR(50) NULL DEFAULT NULL,
  `telefone` VARCHAR(50) NULL DEFAULT NULL,
  `data_cad_usuario` DATE NULL DEFAULT NULL,
  `data_ultima_alteracao_usuario` DATE NULL,
  `sexo` VARCHAR(20) NULL DEFAULT NULL,
  `permissao` VARCHAR(20) NULL DEFAULT NULL,
  `situacao` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Log_Sistema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Log_Sistema` (
  `id_log_sistema` INT(11) NOT NULL AUTO_INCREMENT,
  `data` DATE NULL,
  `hora` VARCHAR(50) NULL DEFAULT NULL,
  `acao` VARCHAR(500) NULL DEFAULT NULL,
  `Usuario_id_usuario` INT(11) NOT NULL,
  PRIMARY KEY (`id_log_sistema`),
  INDEX `fk_Log_Sistema_Usuario1_idx` (`Usuario_id_usuario` ASC),
  CONSTRAINT `fk_Log_Sistema_Usuario1`
    FOREIGN KEY (`Usuario_id_usuario`)
    REFERENCES `coolab`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Lote_Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Lote_Estoque` (
  `id_lote` INT(11) NOT NULL AUTO_INCREMENT,
  `numero_lote` VARCHAR(50) NULL DEFAULT NULL,
  `data_validade_lote` DATE NULL DEFAULT NULL,
  `quantidade_estoque` DOUBLE NULL DEFAULT NULL,
  `situacao` VARCHAR(10) NULL DEFAULT NULL,
  `Produto_id_produto` INT(11) NOT NULL,
  PRIMARY KEY (`id_lote`),
  INDEX `fk_Lote_Medicamento_Produto1_idx` (`Produto_id_produto` ASC),
  CONSTRAINT `fk_Lote_Medicamento_Produto1`
    FOREIGN KEY (`Produto_id_produto`)
    REFERENCES `coolab`.`Produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Turma` (
  `id_turma` INT(11) NOT NULL AUTO_INCREMENT,
  `semestre` INT(11) NULL DEFAULT NULL,
  `turno` CHAR(1) NULL DEFAULT NULL,
  `ano_turma` VARCHAR(10) NULL DEFAULT NULL,
  `semestre_vestibular` INT(11) NULL DEFAULT NULL,
  `situacao_turma` VARCHAR(10) NULL DEFAULT NULL,
  `turma` VARCHAR(10) NULL,
  `data_cad_turma` DATE NULL,
  `data_ultima_alteracao_turma` DATE NULL,
  `Curso_id_curso` INT(11) NOT NULL,
  PRIMARY KEY (`id_turma`),
  INDEX `fk_Turma_Curso1_idx` (`Curso_id_curso` ASC),
  CONSTRAINT `fk_Turma_Curso1`
    FOREIGN KEY (`Curso_id_curso`)
    REFERENCES `coolab`.`Curso` (`id_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Disciplina` (
  `id_disciplina` INT NOT NULL AUTO_INCREMENT,
  `disciplina` VARCHAR(100) NULL,
  `semestre` VARCHAR(45) NULL,
  `situacao_disciplina` VARCHAR(10) NULL,
  `data_cad_disciplina` DATE NULL,
  `data_ultima_alteracao_disciplina` DATE NULL,
  `Curso_id_curso` INT(11) NOT NULL,
  PRIMARY KEY (`id_disciplina`),
  INDEX `fk_Disciplina_Curso1_idx` (`Curso_id_curso` ASC),
  CONSTRAINT `fk_Disciplina_Curso1`
    FOREIGN KEY (`Curso_id_curso`)
    REFERENCES `coolab`.`Curso` (`id_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `coolab`.`Saida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Saida` (
  `id_saida` INT(11) NOT NULL AUTO_INCREMENT,
  `data_saida` DATE NULL DEFAULT NULL,
  `tipo` VARCHAR(100) NULL,
  `observacao` VARCHAR(100) NULL,
  `situacao` VARCHAR(50) NULL,
  `solicita_devolucao_saida` VARCHAR(10) NULL,
  `Turma_id_turma` INT(11) NOT NULL,
  `Disciplina_id_disciplina` INT NOT NULL,
  `data_alteracao_saida` DATE NULL,
  PRIMARY KEY (`id_saida`),
  INDEX `fk_Saida_Turma1_idx` (`Turma_id_turma` ASC),
  INDEX `fk_Saida_Disciplina1_idx` (`Disciplina_id_disciplina` ASC),
  CONSTRAINT `fk_Saida_Turma1`
    FOREIGN KEY (`Turma_id_turma`)
    REFERENCES `coolab`.`Turma` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Saida_Disciplina1`
    FOREIGN KEY (`Disciplina_id_disciplina`)
    REFERENCES `coolab`.`Disciplina` (`id_disciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Saida_Itens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Saida_Itens` (
  `id_saida_itens` INT(11) NOT NULL AUTO_INCREMENT,
  `quantidade` DOUBLE NULL DEFAULT NULL,
  `validade` DATE NULL,
  `lote` VARCHAR(50) NULL,
  `Saida_id_saida` INT(11) NOT NULL,
  `Produto_id_produto` INT(11) NOT NULL,
  `devolvido` VARCHAR(20) NULL,
  PRIMARY KEY (`id_saida_itens`),
  INDEX `fk_Saida_Itens_Produto1_idx` (`Produto_id_produto` ASC),
  INDEX `fk_Saida_Itens_Saida1_idx` (`Saida_id_saida` ASC),
  CONSTRAINT `fk_Saida_Itens_Produto1`
    FOREIGN KEY (`Produto_id_produto`)
    REFERENCES `coolab`.`Produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Saida_Itens_Saida1`
    FOREIGN KEY (`Saida_id_saida`)
    REFERENCES `coolab`.`Saida` (`id_saida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Ajuste_Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Ajuste_Estoque` (
  `id_ajuste_estoque` INT NOT NULL AUTO_INCREMENT,
  `motivo` VARCHAR(50) NULL,
  `observacao` VARCHAR(200) NULL,
  `quantidade` DOUBLE NULL,
  `situacao_ajuste` VARCHAR(50) NULL,
  `data_ajuste` DATE NULL,
  `Produto_id_produto` INT(11) NOT NULL,
  `Lote_Estoque_id_lote` INT(11) NOT NULL,
  `Usuario_id_usuario` INT(11) NOT NULL,
  PRIMARY KEY (`id_ajuste_estoque`),
  INDEX `fk_Ajuste_Estoque_Produto1_idx` (`Produto_id_produto` ASC),
  INDEX `fk_Ajuste_Estoque_Lote_Estoque1_idx` (`Lote_Estoque_id_lote` ASC),
  INDEX `fk_Ajuste_Estoque_Usuario1_idx` (`Usuario_id_usuario` ASC),
  CONSTRAINT `fk_Ajuste_Estoque_Produto1`
    FOREIGN KEY (`Produto_id_produto`)
    REFERENCES `coolab`.`Produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ajuste_Estoque_Lote_Estoque1`
    FOREIGN KEY (`Lote_Estoque_id_lote`)
    REFERENCES `coolab`.`Lote_Estoque` (`id_lote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ajuste_Estoque_Usuario1`
    FOREIGN KEY (`Usuario_id_usuario`)
    REFERENCES `coolab`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `coolab`.`Entrada_Itens_Cancelados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Entrada_Itens_Cancelados` (
  `id_entrada_itens` INT(11) NOT NULL AUTO_INCREMENT,
  `quantidade` DOUBLE NULL,
  `lote` VARCHAR(50) NULL DEFAULT NULL,
  `data_validade` DATE NULL DEFAULT NULL,
  `data_cancelamento_produto` DATE NULL,
  `Produto_id_produto` INT(11) NOT NULL,
  `Entrada_id_entrada` INT(11) NOT NULL,
  PRIMARY KEY (`id_entrada_itens`),
  INDEX `fk_Entrada_Itens_Produto2_idx` (`Produto_id_produto` ASC),
  INDEX `fk_Entrada_Itens_Entrada1_idx` (`Entrada_id_entrada` ASC),
  CONSTRAINT `fk_Entrada_Itens_Entrada10`
    FOREIGN KEY (`Entrada_id_entrada`)
    REFERENCES `coolab`.`Entrada` (`id_entrada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Entrada_Itens_Produto20`
    FOREIGN KEY (`Produto_id_produto`)
    REFERENCES `coolab`.`Produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Motivo_Entrada_Cancelada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Motivo_Entrada_Cancelada` (
  `id_motivo_entrada_cancelada` INT NOT NULL AUTO_INCREMENT,
  `motivo_entrada_cancelada` VARCHAR(500) NULL,
  `Entrada_id_entrada` INT(11) NOT NULL,
  `data_cancelamento` DATE NULL,
  PRIMARY KEY (`id_motivo_entrada_cancelada`),
  INDEX `fk_Motivo_Entrada_Cancelada_Entrada1_idx` (`Entrada_id_entrada` ASC),
  CONSTRAINT `fk_Motivo_Entrada_Cancelada_Entrada1`
    FOREIGN KEY (`Entrada_id_entrada`)
    REFERENCES `coolab`.`Entrada` (`id_entrada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `coolab`.`Motivo_Saida_Cancelada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Motivo_Saida_Cancelada` (
  `id_motivo_saida_cancelada` INT NOT NULL AUTO_INCREMENT,
  `motivo_saida_cancelada` VARCHAR(500) NULL,
  `data_cancelamento` DATE NULL,
  `Saida_id_saida` INT(11) NOT NULL,
  PRIMARY KEY (`id_motivo_saida_cancelada`),
  INDEX `fk_Motivo_Saida_Cancelada_Saida1_idx` (`Saida_id_saida` ASC),
  CONSTRAINT `fk_Motivo_Saida_Cancelada_Saida1`
    FOREIGN KEY (`Saida_id_saida`)
    REFERENCES `coolab`.`Saida` (`id_saida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `coolab`.`Saida_Itens_Cancelados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Saida_Itens_Cancelados` (
  `id_saida_itens` INT(11) NOT NULL AUTO_INCREMENT,
  `quantidade` DOUBLE NULL,
  `lote` VARCHAR(50) NULL DEFAULT NULL,
  `data_validade` DATE NULL DEFAULT NULL,
  `data_cancelamento_produto` DATE NULL,
  `Produto_id_produto` INT(11) NOT NULL,
  `Saida_id_saida` INT(11) NOT NULL,
  PRIMARY KEY (`id_saida_itens`),
  INDEX `fk_Saida_Itens_Cancelados_Produto1_idx` (`Produto_id_produto` ASC),
  INDEX `fk_Saida_Itens_Cancelados_Saida1_idx` (`Saida_id_saida` ASC),
  CONSTRAINT `fk_Saida_Itens_Cancelados_Produto1`
    FOREIGN KEY (`Produto_id_produto`)
    REFERENCES `coolab`.`Produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Saida_Itens_Cancelados_Saida1`
    FOREIGN KEY (`Saida_id_saida`)
    REFERENCES `coolab`.`Saida` (`id_saida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `coolab`.`Saida_Outra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Saida_Outra` (
  `id_saida_outra` INT NOT NULL AUTO_INCREMENT,
  `data_saida_outra` DATE NULL,
  `tipo_outra` VARCHAR(50) NULL,
  `observacao` VARCHAR(500) NULL,
  `situacao` VARCHAR(50) NULL,
  `solicita_devolucao_saida_outra` VARCHAR(10) NULL,
  `data_alteracao_saida_outra` DATE NULL,
  PRIMARY KEY (`id_saida_outra`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `coolab`.`Saida_Itens_Outra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coolab`.`Saida_Itens_Outra` (
  `id_saida_itens` INT(11) NOT NULL AUTO_INCREMENT,
  `quantidade` DOUBLE NULL DEFAULT NULL,
  `validade` DATE NULL,
  `lote` VARCHAR(50) NULL,
  `devolvido` VARCHAR(20) NULL,
  `Produto_id_produto` INT(11) NOT NULL,
  `Saida_Outra_id_saida_outra` INT NOT NULL,
  PRIMARY KEY (`id_saida_itens`),
  INDEX `fk_Saida_Itens_Produto1_idx` (`Produto_id_produto` ASC),
  INDEX `fk_Saida_Itens_Outra_Saida_Outra1_idx` (`Saida_Outra_id_saida_outra` ASC),
  CONSTRAINT `fk_Saida_Itens_Produto10`
    FOREIGN KEY (`Produto_id_produto`)
    REFERENCES `coolab`.`Produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Saida_Itens_Outra_Saida_Outra1`
    FOREIGN KEY (`Saida_Outra_id_saida_outra`)
    REFERENCES `coolab`.`Saida_Outra` (`id_saida_outra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
