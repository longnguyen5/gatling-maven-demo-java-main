var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "400",
        "ok": "316",
        "ko": "84"
    },
    "minResponseTime": {
        "total": "265",
        "ok": "265",
        "ko": "3051"
    },
    "maxResponseTime": {
        "total": "295768",
        "ok": "2740",
        "ko": "295768"
    },
    "meanResponseTime": {
        "total": "2023",
        "ok": "399",
        "ko": "8132"
    },
    "standardDeviation": {
        "total": "14814",
        "ok": "225",
        "ko": "31584"
    },
    "percentiles1": {
        "total": "377",
        "ok": "351",
        "ko": "4194"
    },
    "percentiles2": {
        "total": "666",
        "ok": "405",
        "ko": "5881"
    },
    "percentiles3": {
        "total": "5900",
        "ok": "666",
        "ko": "6004"
    },
    "percentiles4": {
        "total": "6073",
        "ok": "1718",
        "ko": "295768"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 309,
    "percentage": 77.25
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1,
    "percentage": 0.25
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 6,
    "percentage": 1.5
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 84,
    "percentage": 21.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.89",
        "ok": "0.71",
        "ko": "0.19"
    }
},
contents: {
"req_access-news-sec-629658548": {
        type: "REQUEST",
        name: "Access News Section",
path: "Access News Section",
pathFormatted: "req_access-news-sec-629658548",
stats: {
    "name": "Access News Section",
    "numberOfRequests": {
        "total": "400",
        "ok": "316",
        "ko": "84"
    },
    "minResponseTime": {
        "total": "265",
        "ok": "265",
        "ko": "3051"
    },
    "maxResponseTime": {
        "total": "295768",
        "ok": "2740",
        "ko": "295768"
    },
    "meanResponseTime": {
        "total": "2023",
        "ok": "399",
        "ko": "8132"
    },
    "standardDeviation": {
        "total": "14814",
        "ok": "225",
        "ko": "31584"
    },
    "percentiles1": {
        "total": "377",
        "ok": "351",
        "ko": "4194"
    },
    "percentiles2": {
        "total": "666",
        "ok": "405",
        "ko": "5881"
    },
    "percentiles3": {
        "total": "5886",
        "ok": "666",
        "ko": "6004"
    },
    "percentiles4": {
        "total": "48632",
        "ok": "1635",
        "ko": "295768"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 309,
    "percentage": 77.25
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1,
    "percentage": 0.25
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 6,
    "percentage": 1.5
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 84,
    "percentage": 21.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.89",
        "ok": "0.71",
        "ko": "0.19"
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
