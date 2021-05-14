const type = {
    'returnFlag' : {
        true : '需要回传',
        false : '不需要回传'
    },
}

export function typeFormatter(prop,row) {
    console.log(prop,row)
    return type[prop][row[prop]]
}

export default {
    // typeFormatter
}