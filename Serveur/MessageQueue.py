def ajouterMessageRecu(file, message):
    txt = message.split("\n")
    file.append(txt)

def defiler(file):
    if len(file) != 0:
        premier = file[0]
        file.remove(premier)
        return premier 

def recupMessage(file):
    return defiler(file)