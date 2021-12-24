from Ressource import *
SERVER_KO_OPPONENT = "encounter KO opponent\n"
SERVER_KO_PLAYER = "encounter KO player\n"
SERVER_WIN = "encounter win\n"
SERVER_LOSE = "encounter lose\n"
SERVER_CATCH_OK = "encounter catch ok\n"
SERVER_CATCH_FAIL = "encounter catch fail\n"
SERVER_ESCAPE_OK = "encounter escape ok\n"
SERVER_ESCAPE_FAIL = "encounter escape fail\n"
SERVER_DMD_ACTION = "encounter enter action\n"
SERVER_DMD_POKETUDIANT_IND = "encounter enter poketudiant index\n"
SERVER_ACTION_FORBIDDEN = "encounter forbidden action\n"
SERVER_POKETUDIANT_IND_INVALID = "encounter invalid poketudiant index\n"
CLIENT_ATTACK1 = "encounter action attack1\n"
CLIENT_ATTACK2 = "encounter action attack2\n"
CLIENT_SWITCH = "encounter action switch\n"
CLIENT_CATCH = "encounter action catch\n"
CLIENT_LEAVE = "encounter action leave\n"

PREFIX_SENDMSG = "send message "
PREFIX_RIVALMSG = "rival message "

def getEnterEncounter(n, rival):
    ch = "encounter new "
    ch += rival + " " + str(n) + "\n"
    return ch

def getEarnExperienceStr(amount, indPoketudiant):
    ch = "encounter poketudiant xp "
    ch += str(indPoketudiant) + " " + str(amount) + "\n"
    return ch 

def getEarnLvlStr(lvl, indPoketudiant):
    ch = "encounter poketudiant level "
    ch += str(indPoketudiant) + " " + str(lvl) + "\n"
    return ch

def getEvolutionStr(variete, indPoketudiant):
    ch = "encounter poketudiant evolution "
    ch += str(indPoketudiant) + " " + str(variete) + "\n"
    return ch


def isPoketudiantIndexMsg(msg):
    p = "encounter poketudiant index"
    if p in msg:
        return int(mystrtol(msg))
    else:
        return -1