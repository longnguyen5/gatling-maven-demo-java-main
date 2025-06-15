var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "2000",
        "ok": "2000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "16",
        "ok": "16",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "19285",
        "ok": "19285",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "263",
        "ok": "263",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "492",
        "ok": "492",
        "ko": "-"
    },
    "percentiles1": {
        "total": "155",
        "ok": "155",
        "ko": "-"
    },
    "percentiles2": {
        "total": "453",
        "ok": "453",
        "ko": "-"
    },
    "percentiles3": {
        "total": "679",
        "ok": "679",
        "ko": "-"
    },
    "percentiles4": {
        "total": "890",
        "ok": "1633",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 1969,
    "percentage": 98.45
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 29,
    "percentage": 1.4500000000000002
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 2,
    "percentage": 0.1
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "6.49",
        "ok": "6.49",
        "ko": "-"
    }
},
contents: {
"req_access-homepage-2121328426": {
        type: "REQUEST",
        name: "Access Homepage",
path: "Access Homepage",
pathFormatted: "req_access-homepage-2121328426",
stats: {
    "name": "Access Homepage",
    "numberOfRequests": {
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "16",
        "ok": "16",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "19285",
        "ok": "19285",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "47",
        "ok": "47",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "609",
        "ok": "609",
        "ko": "-"
    },
    "percentiles1": {
        "total": "24",
        "ok": "24",
        "ko": "-"
    },
    "percentiles2": {
        "total": "27",
        "ok": "27",
        "ko": "-"
    },
    "percentiles3": {
        "total": "50",
        "ok": "50",
        "ko": "-"
    },
    "percentiles4": {
        "total": "120",
        "ok": "120",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 999,
    "percentage": 99.9
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 0,
    "percentage": 0.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 1,
    "percentage": 0.1
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "3.25",
        "ok": "3.25",
        "ko": "-"
    }
}
    },"req_access-homepage-347374531": {
        type: "REQUEST",
        name: "Access Homepage Redirect 1",
path: "Access Homepage Redirect 1",
pathFormatted: "req_access-homepage-347374531",
stats: {
    "name": "Access Homepage Redirect 1",
    "numberOfRequests": {
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "280",
        "ok": "280",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1210",
        "ok": "1210",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "480",
        "ok": "480",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "141",
        "ok": "141",
        "ko": "-"
    },
    "percentiles1": {
        "total": "453",
        "ok": "453",
        "ko": "-"
    },
    "percentiles2": {
        "total": "545",
        "ok": "545",
        "ko": "-"
    },
    "percentiles3": {
        "total": "742",
        "ok": "742",
        "ko": "-"
    },
    "percentiles4": {
        "total": "931",
        "ok": "931",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 970,
    "percentage": 97.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 29,
    "percentage": 2.9000000000000004
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 1,
    "percentage": 0.1
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "3.25",
        "ok": "3.25",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
